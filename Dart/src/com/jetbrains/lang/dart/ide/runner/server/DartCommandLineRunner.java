package com.jetbrains.lang.dart.ide.runner.server;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.DefaultProgramRunner;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @author: Fedor.Korotkov
 */
public class DartCommandLineRunner extends DefaultProgramRunner {
  public static final String DART_RUNNER_ID = "DartCommandLineRunner";

  @NotNull
  @Override
  public String getRunnerId() {
    return DART_RUNNER_ID;
  }

  @Override
  public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
    return DefaultRunExecutor.EXECUTOR_ID.equals(executorId) && profile instanceof DartCommandLineRunConfiguration;
  }

  @Override
  protected RunContentDescriptor doExecute(Project project,
                                           RunProfileState state,
                                           RunContentDescriptor contentToReuse,
                                           ExecutionEnvironment env) throws ExecutionException {
    final DartCommandLineRunConfiguration configuration = (DartCommandLineRunConfiguration)env.getRunProfile();
    final Module module = configuration.getModule();

    final String filePath = configuration.getFilePath();
    assert filePath != null;

    final DartCommandLineRunningState dartCommandLineRunningState = new DartCommandLineRunningState(
      env,
      module,
      filePath,
      StringUtil.notNullize(configuration.getVMOptions()),
      StringUtil.notNullize(configuration.getArguments())
    );

    return super.doExecute(project, dartCommandLineRunningState, contentToReuse, env);
  }
}
