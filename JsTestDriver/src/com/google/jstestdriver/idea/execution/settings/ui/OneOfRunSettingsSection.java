package com.google.jstestdriver.idea.execution.settings.ui;

import com.google.common.collect.Maps;
import com.google.jstestdriver.idea.execution.settings.JstdRunSettings;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * All methods should be executed on EDT.
 */
class OneOfRunSettingsSection<T extends IdProvider & RunSettingsSectionProvider> extends AbstractRunSettingsSection {

  private T mySelectedKey;
  private JPanel myCardPanel;
  private final Collection<T> myRunSettingsSectionProviders;
  private final Map<String, RunSettingsSection> mySectionByIdMap = Maps.newHashMap();

  public OneOfRunSettingsSection(@NotNull Collection<T> runSettingsSectionProviders) {
    myRunSettingsSectionProviders = runSettingsSectionProviders;
  }

  @Override
  public void resetFrom(@NotNull JstdRunSettings runSettings) {
    RunSettingsSection runSettingsSection = getSelectedRunSettingsSection();
    runSettingsSection.resetFrom(runSettings);
  }

  @Override
  public void applyTo(@NotNull JstdRunSettings.Builder runSettingsBuilder) {
    RunSettingsSection runSettingsSection = getSelectedRunSettingsSection();
    runSettingsSection.applyTo(runSettingsBuilder);
  }

  @NotNull
  @Override
  protected JPanel createComponent(@NotNull CreationContext creationContext) {
    myCardPanel = new JPanel(new CardLayout());
    for (T child : myRunSettingsSectionProviders) {
      RunSettingsSection runSettingsSection = child.provideRunSettingsSection();
      JComponent comp = runSettingsSection.getComponent(creationContext);
      myCardPanel.add(comp, child.getId());
      mySectionByIdMap.put(child.getId(), runSettingsSection);
    }
    Iterator<T> iterator = myRunSettingsSectionProviders.iterator();
    if (iterator.hasNext()) {
      select(iterator.next());
    } else {
      throw new RuntimeException("No child items were found");
    }
    myAnchor = UIUtil.mergeComponentsWithAnchor(mySectionByIdMap.values());
    return myCardPanel;
  }

  public void select(@NotNull T key) {
    if (mySelectedKey != key) {
      CardLayout cardLayout = (CardLayout) myCardPanel.getLayout();
      cardLayout.show(myCardPanel, key.getId());
      mySelectedKey = key;
    }
  }

  public T getSelectedKey() {
    return mySelectedKey;
  }

  private RunSettingsSection getSelectedRunSettingsSection() {
    return mySectionByIdMap.get(mySelectedKey.getId());
  }

  @Override
  public void setAnchor(@Nullable JComponent anchor) {
    super.setAnchor(anchor);
    for (RunSettingsSection runSettingsSection : mySectionByIdMap.values()) {
      runSettingsSection.setAnchor(anchor);
    }
  }
}
