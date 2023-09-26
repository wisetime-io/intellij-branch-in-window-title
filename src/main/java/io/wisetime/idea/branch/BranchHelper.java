package io.wisetime.idea.branch;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectEx;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.impl.FrameTitleBuilder;
import javax.swing.JFrame;

public final class BranchHelper {

  private final Logger logger = Logger.getInstance("FrameHelper");

  private final Project project;
  private String currentBranchName;

  public BranchHelper(Project project) {
    this.project = project;
  }

  public String getCurrentBranchName() {
    return currentBranchName;
  }

  public void onBranchChanged(String branchName) {
    currentBranchName = branchName;
    ApplicationManager.getApplication().invokeLater(this::updateFrameTitle);
  }

  private void updateFrameTitle() {
    try {
      final String projectTitle = FrameTitleBuilder.getInstance().getProjectTitle(project);
      updateProjectTitle(projectTitle);
      updateWindowTitle(projectTitle);
    } catch (Exception e) {
      logger.error("error while updating title", e);
    }
  }

  private void updateWindowTitle(String projectTitle) {
    JFrame ideFrame = WindowManager.getInstance().getFrame(project);
    if (ideFrame != null) {
      ideFrame.setTitle(projectTitle);
    } else {
      logger.info("unable to obtain IdeFrame (WindowManager returned null ideFrame)");
    }
  }

  private void updateProjectTitle(String projectTitle) {
    if (project instanceof ProjectEx) {
      ((ProjectEx) project).setProjectName(projectTitle);
    } else {
      logger.warn("Can't update branch title to " + project);
    }
  }

}
