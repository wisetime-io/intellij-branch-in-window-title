/*
 * Copyright (c) 2021 Practice Insight Pty Ltd. All Rights Reserved.
 */

package io.wisetime.idea.branch.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.wm.impl.IdeFrameDecorator;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author vadym
 */
@State(
    name = "io.wisetime.idea.branch.settings.SettingsState",
    storages = @Storage("WTBranchWindowTitleSettings.xml")
)
public class SettingsState implements PersistentStateComponent<SettingsState> {

  public boolean appendToWindowTitle = true;
  public boolean appendToProjectName = isCustomDecoratorActive();

  public static SettingsState getInstance() {
    return ApplicationManager.getApplication().getService(SettingsState.class);
  }

  @Override
  public @Nullable SettingsState getState() {
    return this;
  }

  @Override
  public void loadState(@NotNull SettingsState state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  private boolean isCustomDecoratorActive() {
    try {
      return IdeFrameDecorator.isCustomDecorationActive();
    } catch (NoSuchMethodError e) {
      // ignore if method is unavailable
      return false;
    }
  }

}
