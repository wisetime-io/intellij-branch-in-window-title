package io.wisetime.idea.branch

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.startup.StartupActivity
import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.util.Consumer
import org.jetbrains.idea.svn.SvnUtil
import org.jetbrains.idea.svn.SvnVcs

class SubversionListener : StartupActivity {

  override fun runActivity(project: Project) {
    project.messageBus.connect().subscribe(SvnVcs.ROOTS_RELOADED, Consumer<Any> {
        val svnVcs = SvnVcs.getInstance(project)
        if (svnVcs != null) {
          project.guessProjectDir()?.let {rootDir ->
            val branch = SvnUtil.getUrl(svnVcs, VfsUtilCore.virtualToIoFile(rootDir))
            if (branch != null) {
              val root = svnVcs.svnFileUrlMapping.getWcRootForUrl(branch)
              if (root != null) {
                val relativePath = SvnUtil.getRelativePath(root.repositoryUrl.path, branch.path)
                if (relativePath != null) {
                  notifyBranchChanged(project, "^/$relativePath")
                }
              }
            }
          }
        }
      })
  }

  private fun notifyBranchChanged(project: Project, branchName: String) {
    val service = project.getService(BranchHelper::class.java)
    if (service == null) {
      thisLogger().warn("Failed to notify branch change")
      return
    }
    service.currentBranchName = branchName
  }
}
