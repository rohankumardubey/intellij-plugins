package com.intellij.tapestry.tests;

import com.intellij.facet.FacetManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.RunResult;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.psi.PsiFile;
import com.intellij.tapestry.core.TapestryProject;
import com.intellij.tapestry.intellij.TapestryModuleSupportLoader;
import com.intellij.tapestry.intellij.facet.TapestryFacet;
import com.intellij.tapestry.intellij.facet.TapestryFacetType;
import com.intellij.tapestry.intellij.util.TapestryUtils;
import com.intellij.testFramework.UsefulTestCase;
import com.intellij.testFramework.builders.JavaModuleFixtureBuilder;
import com.intellij.testFramework.fixtures.*;
import com.intellij.util.ArrayUtil;
import junit.framework.Assert;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.io.IOException;

/**
 * @author Alexey Chmutov
 *         Date: Jul 13, 2009
 *         Time: 3:49:34 PM
 */
public abstract class TapestryBaseTestCase extends UsefulTestCase {
  static final String TEST_APPLICATION_PACKAGE = "com.testapp";
  static final String COMPONENTS = "components";
  static final String PAGES = "pages";
  static final String COMPONENTS_PACKAGE_PATH = TEST_APPLICATION_PACKAGE.replace('.', '/') + "/" + COMPONENTS + "/";
  static final String PAGES_PACKAGE_PATH = TEST_APPLICATION_PACKAGE.replace('.', '/') + "/" + PAGES + "/";

  @NonNls
  protected abstract String getBasePath();

  @NonNls
  protected final String getTestDataPath() {
    return Util.getCommonTestDataPath() + getBasePath();
  }

  protected CodeInsightTestFixture myFixture;
  protected Project myProject;
  protected Module myModule;

  protected Class<? extends JavaModuleFixtureBuilder> getModuleFixtureBuilderClass() {
    return JavaModuleFixtureBuilder.class;
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    final TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder = JavaTestFixtureFactory.createFixtureBuilder();
    JavaModuleFixtureBuilder moduleBuilder = projectBuilder.addModule(getModuleFixtureBuilderClass());
    myFixture = IdeaTestFixtureFactory.getFixtureFactory().createCodeInsightFixture(projectBuilder.getFixture());
    myFixture.setTestDataPath(getTestDataPath());
    configureModule(moduleBuilder);

    myFixture.setUp();
    myProject = myFixture.getProject();
    myModule = moduleBuilder.getFixture().getModule();

    createFacet();
  }

  protected TapestryFacet createFacet() {
    final RunResult<TapestryFacet> runResult = new WriteCommandAction<TapestryFacet>(myProject) {
      protected void run(final Result<TapestryFacet> result) throws Throwable {
        final TapestryFacetType facetType = TapestryFacetType.INSTANCE;
        final FacetManager facetManager = FacetManager.getInstance(myModule);
        final TapestryFacet facet = facetManager.addFacet(facetType, facetType.getPresentableName(), null);
        facet.getConfiguration().setApplicationPackage(TEST_APPLICATION_PACKAGE);
        result.setResult(facet);
        Assert.assertNotNull(facetManager.getFacetByType(TapestryFacetType.ID));
      }
    }.execute();
    if (runResult.hasException()) {
      throw new RuntimeException(runResult.getThrowable());
    }
    Assert.assertTrue("Not Tapestry module", TapestryUtils.isTapestryModule(myModule));
    Assert.assertNotNull("No TapestryModuleSupportLoader", TapestryModuleSupportLoader.getInstance(myModule));
    final TapestryProject tapestryProject = TapestryModuleSupportLoader.getTapestryProject(myModule);
    Assert.assertNotNull("No TapestryProject", tapestryProject);
    Assert.assertNotNull(tapestryProject.getApplicationRootPackage());
    Assert.assertNotNull(tapestryProject.getApplicationLibrary());
    return runResult.getResultObject();
  }

  protected void configureModule(JavaModuleFixtureBuilder moduleBuilder) throws Exception {
    moduleBuilder.addContentRoot(myFixture.getTempDirPath());
    moduleBuilder.addSourceRoot("");
    moduleBuilder.setMockJdkLevel(JavaModuleFixtureBuilder.MockJdkLevel.jdk15);
    addTapestryLibraries(moduleBuilder);
  }

  protected void addTapestryLibraries(final JavaModuleFixtureBuilder moduleBuilder) {
    moduleBuilder.addLibraryJars("tapestry_5.1.0.5", Util.getCommonTestDataPath() + "libs", "antlr-runtime-3.1.1.jar", "commons-codec.jar",
                                 "javassist.jar", "log4j.jar", "slf4j-api.jar", "slf4j-log4j12.jar", "stax2.jar",
                                 "tapestry5-annotations.jar", "tapestry-core.jar", "tapestry-ioc.jar", "wstx-asl.jar");
  }

  protected String getElementTagName() {
    return "t:" + getLowerCaseElementName();
  }

  protected String getLowerCaseElementName() {
    return getElementName().toLowerCase();
  }

  protected String getElementName() {
    return getTestName(false);
  }

  protected String getElementClassFileName() {
    return getElementName() + Util.DOT_JAVA;
  }

  protected String getElementTemplateFileName() {
    return getElementName() + Util.DOT_TML;
  }

  protected void initByComponent() throws IOException {
    initByComponent(true);
  }

  protected VirtualFile initByComponent(boolean configureByTmlNotJava) throws IOException {
    VirtualFile javaFile = copyOrCreateComponentClassFile(getElementClassFileName());
    final String tmlName = getElementTemplateFileName();
    VirtualFile tmlFile = myFixture.copyFileToProject(tmlName, COMPONENTS_PACKAGE_PATH + tmlName);
    final VirtualFile result = configureByTmlNotJava ? tmlFile : javaFile;
    myFixture.configureFromExistingVirtualFile(result);
    return result;
  }

  protected File getFileByPath(@NonNls String filePath) {
    return new File(myFixture.getTestDataPath() + "/" + filePath);
  }

  protected VirtualFile copyOrCreateComponentClassFile(@NonNls String classFileName) throws IOException {
    String targetPath = COMPONENTS_PACKAGE_PATH + classFileName;
    VirtualFile destFile;
    if (getFileByPath(classFileName).exists()) {
      destFile = myFixture.copyFileToProject(classFileName, targetPath);
      myFixture.allowTreeAccessForFile(destFile);
    }
    else {
      addFileAndAllowTreeAccess(targetPath, "package " + TEST_APPLICATION_PACKAGE + "." + COMPONENTS + "; public class " + getElementName() + " {}");
      File ioFile = new File(myFixture.getTempDirPath() + "/" + targetPath);
      destFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(ioFile);
    }
    Assert.assertNotNull(destFile);
    return destFile;
  }

  protected void addComponentToProject(String className) throws IOException {
    addElementToProject(COMPONENTS_PACKAGE_PATH, className, Util.DOT_JAVA);
  }

  protected void addPageToProject(String className) throws IOException {
    addElementToProject(PAGES_PACKAGE_PATH, className, Util.DOT_TML);
    addElementToProject(PAGES_PACKAGE_PATH, className, Util.DOT_JAVA);
  }

  private void addElementToProject(String relativePath, String className, String ext) throws IOException {
    final int afterDotIndex = className.lastIndexOf('.');
    String fileText;
    if (afterDotIndex != -1) {
      final String subpackage = className.substring(0, afterDotIndex);
      relativePath += subpackage.replace('.', '/') + '/';
      className = className.substring(afterDotIndex + 1);
      fileText = Util.getCommonTestDataFileText(className + ext);
      if (fileText.startsWith("package " + TEST_APPLICATION_PACKAGE)) {
        int toPasteSubpackageIndex = fileText.indexOf(';');
        fileText = fileText.substring(0, toPasteSubpackageIndex) + '.' + subpackage + fileText.substring(toPasteSubpackageIndex);
      }
    }
    else {
      fileText = Util.getCommonTestDataFileText(className + ext);
    }
    addFileAndAllowTreeAccess(relativePath + className + ext, fileText);
  }

  private void addFileAndAllowTreeAccess(String targetPath, String fileText) throws IOException {
    final PsiFile file = myFixture.addFileToProject(targetPath, fileText);
    Assert.assertNotNull(file);
    Assert.assertNotNull(file.getVirtualFile());
    myFixture.allowTreeAccessForFile(file.getVirtualFile());
  }

  protected static String[] mergeArrays(String[] array, @NonNls String... list) {
    return ArrayUtil.mergeArrays(array, list, String.class);
  }

  @Override
  protected void tearDown() throws Exception {
    myFixture.tearDown();
    myFixture = null;
    myProject = null;
    myModule = null;
    super.tearDown();
  }
}



