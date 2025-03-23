package io.github.nyub.selfieintellijplugin.language.structure

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.platform.backend.navigation.NavigationRequest
import com.intellij.pom.Navigatable
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.util.PsiTreeUtil
import io.github.nyub.selfieintellijplugin.language.SelfieFile
import io.github.nyub.selfieintellijplugin.language.psi.SelfiePsiExtensions.pathAndFacet
import io.github.nyub.selfieintellijplugin.language.psi.SelfieSnapshot
import io.github.nyub.selfieintellijplugin.language.psi.SelfieSnapshotImpl

class SelfieStructureViewElement(private val element: NavigatablePsiElement) : StructureViewTreeElement,
    SortableTreeElement, Navigatable {
    override fun getPresentation(): ItemPresentation = when (element) {
        is SelfieFile -> PresentationData(element.name, "", icon, null)
        is SelfieSnapshot -> PresentationData(element.pathAndFacet, "", icon, null)
        else -> PresentationData("<MISSING REPRESENTATION>", "", icon, null)
    }

    override fun getChildren(): Array<TreeElement> {
        return when (element) {
            is SelfieFile -> PsiTreeUtil.getChildrenOfTypeAsList(element, SelfieSnapshot::class.java).map {
                SelfieStructureViewElement(it as SelfieSnapshotImpl)
            }.toTypedArray()

            else -> emptyArray()
        }
    }

    override fun getValue(): Any = element

    override fun getAlphaSortKey(): String = when (element) {
        is SelfieSnapshot -> element.pathAndFacet
        else -> element.name ?: ""
    }

    override fun canNavigateToSource(): Boolean = element.canNavigateToSource()

    override fun navigate(requestFocus: Boolean) = element.navigate(requestFocus)

    override fun canNavigate(): Boolean = element.canNavigate()

    override fun navigationRequest(): NavigationRequest? = element.navigationRequest()

    companion object {
        @JvmStatic
        val icon = AllIcons.Ide.NavBarSeparator
    }
}