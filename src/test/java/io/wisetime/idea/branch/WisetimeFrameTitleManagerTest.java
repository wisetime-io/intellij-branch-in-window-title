package io.wisetime.idea.branch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.intellij.openapi.project.Project;
import org.junit.jupiter.api.Test;

/**
 * @author thomas.haines
 */
public class WisetimeFrameTitleManagerTest {

  @Test
  public void testLogMsgCreate() {
    Project project = mock(Project.class);

    WisetimeFrameTitleManager titleManager = new WisetimeFrameTitleManager();
    String originalProjectTitle = "project-title [~/path/to/the/active repository]";

    when(project.getName())
        .thenReturn(originalProjectTitle);

    assertThat(titleManager.getProjectTitleWithBranch(project, "newBranch"))
        .as("expecting to replace path to repository with branch name")
        .isEqualTo("project-title [newBranch]");

    assertThat(titleManager.getProjectTitleWithBranch(project, null))
        .as("expecting to keep original project title")
        .isEqualTo(originalProjectTitle);
  }

  @Test
  public void testLogMsgCreate_withPath() {
    Project project = mock(Project.class);

    WisetimeFrameTitleManager titleManager = new WisetimeFrameTitleManager();
    String originalProjectTitle = "project-title [~/path/to/the/active repository] - ..\\foo";

    when(project.getName())
        .thenReturn(originalProjectTitle);

    assertThat(titleManager.getProjectTitleWithBranch(project, "newBranch"))
        .as("expecting to replace path to repository with branch name")
        .isEqualTo("project-title [newBranch] - ..\\foo");

    assertThat(titleManager.getProjectTitleWithBranch(project, null))
        .as("expecting to keep original project title")
        .isEqualTo(originalProjectTitle);
  }

}
