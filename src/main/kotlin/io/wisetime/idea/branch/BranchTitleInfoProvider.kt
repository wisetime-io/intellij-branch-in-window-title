package io.wisetime.idea.branch

import com.intellij.openapi.Disposable
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.impl.TitleInfoProvider

class BranchTitleInfoProvider : TitleInfoProvider {

  override val borderlessPrefix: String
    get() = " "

  override val borderlessSuffix: String
    get() = ""

  override fun addUpdateListener(
    project: Project,
    disp: Disposable,
    value: (provider: TitleInfoProvider) -> Unit
  ) {
  }

  override fun getValue(project: Project): String {
    val service = project.getService(BranchHelper::class.java)
    if (service == null) {
      thisLogger().warn("Failed to get BranchHelper service")
      return ""
    }
    return service.currentBranchName ?: ""
  }

  override fun isActive(project: Project): Boolean {
    return true
  }
}
