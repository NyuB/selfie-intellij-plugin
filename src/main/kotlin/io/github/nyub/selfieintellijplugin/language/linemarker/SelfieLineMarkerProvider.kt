package io.github.nyub.selfieintellijplugin.language.linemarker

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import io.github.nyub.selfieintellijplugin.language.SelfieFile
import io.github.nyub.selfieintellijplugin.language.SelfieFileType

class SelfieLineMarkerProvider : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>>
    ) {
        if (element is PsiClass) addJavaClassLinkToSnapshot(element, result)
    }

    private fun addJavaClassLinkToSnapshot(
        element: PsiClass,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>>
    ) {
        val containingClassName = element.name ?: return
        val selfieFile = findSelfieFileForName(element.project, containingClassName) ?: return
        val navigation = NavigationGutterIconBuilder.create(SelfieFileType.icon)
            .setTargets(selfieFile)
            .setTooltipText("Navigate to snapshot file")
        result.add(navigation.createLineMarkerInfo(element))
    }

    private fun findSelfieFileForName(project: Project, name: String): SelfieFile? {
        val selfieFiles = FileTypeIndex.getFiles(SelfieFileType, GlobalSearchScope.allScope(project))
        return selfieFiles.firstOrNull { it.nameWithoutExtension.endsWith(name) }?.let {
            PsiManager.getInstance(project).findFile(it) as? SelfieFile
        }
    }
}