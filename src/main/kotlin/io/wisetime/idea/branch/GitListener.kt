package io.wisetime.idea.branch

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import git4idea.GitLocalBranch
import git4idea.branch.GitBranchUtil
import git4idea.repo.GitRepository
import git4idea.repo.GitRepositoryChangeListener
import java.util.Optional

class GitListener : StartupActivity {

  override fun runActivity(project: Project) {
    val chaneListener = GitRepositoryChangeListener { repository: GitRepository ->
      val currentBranch = repository.currentBranch
      if (currentBranch != null) {
        notifyBranchChanged(repository.project, currentBranch.name)
      }
    }

    project.messageBus.connect().subscribe(GitRepository.GIT_REPO_CHANGE, chaneListener)

    Optional.ofNullable(GitBranchUtil.getCurrentRepository(project))
      .map { obj: GitRepository -> obj.currentBranch }
      .map { obj: GitLocalBranch? -> obj!!.name }
      .ifPresent { branchName: String ->
        notifyBranchChanged(
          project,
          branchName
        )
      }
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
