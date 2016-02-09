package main.java.org.aytartana.intellij.plugin.projectpanedecorator;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.ide.projectView.impl.nodes.PsiDirectoryNode;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import git4idea.GitLocalBranch;
import git4idea.GitReference;
import git4idea.GitUtil;
import git4idea.branch.GitBranchUtil;
import git4idea.repo.GitRepository;


/**
 * Created by jonathan on 12/18/14.
 */
public class ProjectPaneDecorator implements ProjectViewNodeDecorator {

  @Override
  public void decorate(ProjectViewNode projectViewNode, PresentationData presentationData) {
    if (projectViewNode instanceof ClassTreeNode) {
      doClassLevelText(projectViewNode, presentationData);
    } else if (projectViewNode instanceof PsiDirectoryNode) {
      doFolderLevelText(projectViewNode, presentationData);
    }
  }

  private void doFolderLevelText(ProjectViewNode projectViewNode, PresentationData presentationData) {
    presentationData.clearText();
    presentationData.addText(presentationData.getPresentableText(), SimpleTextAttributes.REGULAR_ATTRIBUTES);

    PsiDirectoryNode directoryNode = (PsiDirectoryNode) projectViewNode;
    Module modulo = ModuleUtil.findModuleForFile(directoryNode.getVirtualFile(), projectViewNode.getProject());

    boolean estoyEnNodoModulo = (modulo != null && modulo.getName().equals(directoryNode.getName()));

    if (estoyEnNodoModulo) {
      presentationData.addText(getGitCurrentBranch(projectViewNode.getProject(), directoryNode.getVirtualFile()), SimpleTextAttributes.LINK_ATTRIBUTES);
    }
  }

  private void doClassLevelText(ProjectViewNode projectViewNode, PresentationData presentationData) {
    // nothing to do
  }

  private String getGitCurrentBranch(Project project, VirtualFile fichero) {
    GitLocalBranch gitbranch = GitBranchUtil.getCurrentBranch(project, fichero);
    String text = "";
    if (gitbranch != null) {
      text = gitbranch.getName();
    }
    return "   <" + text + ">";
  }

  private String getGitCommitsToPush(Project project, VirtualFile fichero) {
    String text = "";
    text = "esto es una prueba : 99 commits to push";
    return text;
  }

  private String getGitCommitsToPull(Project project, VirtualFile fichero) {
    String text = "";

    return text;
  }


  @Override
  public void decorate(PackageDependenciesNode packageDependenciesNode, ColoredTreeCellRenderer coloredTreeCellRenderer) {
  }
}
