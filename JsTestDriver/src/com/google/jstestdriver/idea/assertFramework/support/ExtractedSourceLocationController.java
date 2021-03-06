package com.google.jstestdriver.idea.assertFramework.support;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.DocumentAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/*
 * All operations should be executed on EDT.
 */
public class ExtractedSourceLocationController {

  private static final Logger LOG = Logger.getInstance(ExtractedSourceLocationController.class);

  private final Project myProject;
  private final JPanel myDirectoryTypeContent;
  private final String myAssertionFrameworkName;
  private final File myDefaultDir;
  private final List<ChangeListener> myChangeListeners = Lists.newArrayList();
  private TextFieldWithBrowseButton myCustomDirectoryTextFieldWithBrowseButton;
  private DirectoryType mySelectedDirectoryType;

  private ExtractedSourceLocationController(@NotNull Project project,
                                            @NotNull JPanel directoryTypeContent,
                                            @NotNull String assertionFrameworkName) {
    myProject = project;
    myDirectoryTypeContent = directoryTypeContent;
    myAssertionFrameworkName = assertionFrameworkName;
    myDefaultDir = getDefaultDir(assertionFrameworkName);
  }

  private void populate(@NotNull JRadioButton defaultRadioButton, @NotNull JRadioButton customRadioButton) {
    addContentForType(defaultRadioButton, DirectoryType.DEFAULT, new Supplier<JPanel>() {
      @Override
      public JPanel get() {
        JPanel defaultDirectoryTypePanel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField(myDefaultDir.getPath());
        textField.setEditable(false);
        defaultDirectoryTypePanel.add(textField, BorderLayout.CENTER);
        return defaultDirectoryTypePanel;
      }
    });

    addContentForType(customRadioButton, DirectoryType.CUSTOM, new Supplier<JPanel>() {
      @Override
      public JPanel get() {
        FileChooserDescriptor fileChooserDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        String adapterName = myAssertionFrameworkName + " adapter";
        String title = "Select a folder for " + adapterName;
        String description = adapterName + " source files will be extracted into the selected folder";
        myCustomDirectoryTextFieldWithBrowseButton = new TextFieldWithBrowseButton();
        myCustomDirectoryTextFieldWithBrowseButton.addBrowseFolderListener(
          title, description, myProject, fileChooserDescriptor
        );
        myCustomDirectoryTextFieldWithBrowseButton.getTextField().getDocument().addDocumentListener(new DocumentAdapter() {
          @Override
          protected void textChanged(DocumentEvent e) {
            fireExtractDirectoryChanged();
          }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(myCustomDirectoryTextFieldWithBrowseButton, BorderLayout.NORTH);
        return panel;
      }
    });

    selectDirectoryType(DirectoryType.DEFAULT);
  }

  private void addContentForType(JRadioButton radioButton,
                                 DirectoryType directoryType,
                                 Supplier<JPanel> producer) {
    JPanel panel = producer.get();
    if (panel == null) {
      throw new RuntimeException("Child panel is null!");
    }
    myDirectoryTypeContent.add(panel, directoryType.name());
    radioButton.addActionListener(new SelectDirectoryTypeActionListener(directoryType));
  }

  public static ExtractedSourceLocationController install(
      @NotNull Project project,
      @NotNull JPanel directoryTypeContent,
      @NotNull String assertionFrameworkName,
      @NotNull JRadioButton defaultRadioButton,
      @NotNull JRadioButton customRadioButton,
      @NotNull Collection<? extends ChangeListener> changeListeners
  ) {
    ExtractedSourceLocationController locationController = new ExtractedSourceLocationController(
      project, directoryTypeContent, assertionFrameworkName
    );
    locationController.populate(defaultRadioButton, customRadioButton);
    for (ChangeListener listener : changeListeners) {
      locationController.addChangeListener(listener);
    }
    locationController.selectDirectoryType(DirectoryType.DEFAULT);
    return locationController;
  }

  public void addChangeListener(ChangeListener changeListener) {
    myChangeListeners.add(changeListener);
  }

  private File getExtractDir() {
    if (mySelectedDirectoryType == DirectoryType.DEFAULT) {
      return myDefaultDir;
    }
    return new File(myCustomDirectoryTextFieldWithBrowseButton.getText());
  }

  @Nullable
  public ValidationInfo validate() {
    if (mySelectedDirectoryType == DirectoryType.DEFAULT) {
      return null;
    }
    File extractDirectory = getExtractDir();
    if (!extractDirectory.isDirectory()) {
      return new ValidationInfo("'" + extractDirectory.getPath() + "' is not a directory.",
                                              myCustomDirectoryTextFieldWithBrowseButton);
    }
    return null;
  }

  private void selectDirectoryType(@NotNull DirectoryType directoryType) {
    CardLayout cardLayout = (CardLayout) myDirectoryTypeContent.getLayout();
    cardLayout.show(myDirectoryTypeContent, directoryType.name());
    mySelectedDirectoryType = directoryType;
    fireExtractDirectoryChanged();
  }

  private void fireExtractDirectoryChanged() {
    File extractDir = getExtractDir();
    for (ChangeListener changeListener : myChangeListeners) {
      changeListener.onExtractDirectoryChanged(extractDir);
    }
  }

  /**
   *
   * @param bundledAdapterFiles files for coping
   * @return extracted file list or null if extraction was failed
   */
  @Nullable
  public List<VirtualFile> extractAdapterFiles(@NotNull final List<VirtualFile> bundledAdapterFiles) {
    return ApplicationManager.getApplication().runWriteAction(new Computable<List<VirtualFile>>() {
      @Override
      @Nullable
      public List<VirtualFile> compute() {
        try {
          VirtualFile extractDir = getOrCreateExtractDirVirtualFile();
          return copyVirtualFilesToDir(bundledAdapterFiles, extractDir);
        } catch (Exception e) {
          LOG.warn("Extraction of " + myAssertionFrameworkName + " adapter files failed", e);
          return null;
        }
      }
    });
  }

  @NotNull
  private VirtualFile getOrCreateExtractDirVirtualFile() {
    if (mySelectedDirectoryType == DirectoryType.DEFAULT) {
      if (!myDefaultDir.isDirectory() && !myDefaultDir.mkdirs()) {
        throw new RuntimeException("Can't create dir " + myDefaultDir.getAbsolutePath());
      }
    }
    File extractDir = getExtractDir();
    VirtualFile vfExtractDir = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(extractDir);
    if (vfExtractDir == null) {
      throw new RuntimeException("Can't find VirtualFile for " + extractDir.getAbsolutePath());
    }
    return vfExtractDir;
  }

  @SuppressWarnings({"NullableProblems"})
  @Nullable
  private List<VirtualFile> copyVirtualFilesToDir(List<VirtualFile> virtualFiles, @NotNull VirtualFile targetDir) {
    List<VirtualFile> copiedFiles = Lists.newArrayList();
    for (VirtualFile virtualFile : virtualFiles) {
      try {
        copiedFiles.add(VfsUtilCore.copyFile(null, virtualFile, targetDir));
      } catch (IOException e) {
        Messages.showErrorDialog("Extract operation failed!\nUnable to copy " + virtualFile.getPath() + " to " + targetDir.getPath(),
            "Adding " + myAssertionFrameworkName + " adapter support for JsTestDriver");
        return null;
      }
    }
    return copiedFiles;
  }

  private static File getDefaultDir(String assertionFrameworkName) {
    File file = new File(PathManager.getSystemPath(), "extLibs/" + assertionFrameworkName + "AdapterForJsTestDriver");
    try {
      return file.getCanonicalFile();
    } catch (IOException e) {
      return file;
    }
  }

  private class SelectDirectoryTypeActionListener implements ActionListener {

    private final DirectoryType myDirectoryType;

    private SelectDirectoryTypeActionListener(@NotNull DirectoryType directoryType) {
      myDirectoryType = directoryType;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      selectDirectoryType(myDirectoryType);
    }
  }

  interface ChangeListener {
    void onExtractDirectoryChanged(File extractDirectory);
  }

  private enum DirectoryType {
    DEFAULT, CUSTOM
  }

}
