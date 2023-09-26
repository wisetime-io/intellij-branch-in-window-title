/*
 * Copyright (c) 2021 Practice Insight Pty Ltd. All Rights Reserved.
 */

package io.wisetime.idea.branch.settings;

import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.ui.components.JBCheckBox;
import javax.swing.JPanel;

/**
 * @author vadym
 */
public class SettingsPanel {

  private final JBCheckBox projectName;
  private final JPanel root;

  public SettingsPanel() {
    root = new JPanel(new VerticalFlowLayout());
    projectName = new JBCheckBox("Append VCS branch name to project name (Experimental)");
    root.add(projectName);
  }

  public void reset() {
    SettingsState state = SettingsState.getInstance();
    projectName.setSelected(state.appendToProjectName);
  }

  public void apply() {
    SettingsState state = SettingsState.getInstance();
    state.appendToProjectName = projectName.isSelected();
  }

  public boolean isModified() {
    SettingsState state = SettingsState.getInstance();
    return state.appendToProjectName != projectName.isSelected();
  }

  public JPanel getPanel() {
    return root;
  }
}
