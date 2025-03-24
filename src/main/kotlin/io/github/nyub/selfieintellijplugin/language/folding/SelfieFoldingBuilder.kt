package io.github.nyub.selfieintellijplugin.language.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType
import io.github.nyub.selfieintellijplugin.language.SelfieFile
import io.github.nyub.selfieintellijplugin.language.psi.SelfieSnapshot

class SelfieFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(element: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        if (element !is SelfieFile) return emptyArray()
        val snapshots = element
            .childrenOfType<SelfieSnapshot>()
            .map { it.body }
        val result = ArrayList<FoldingDescriptor>(snapshots.size)
        snapshots.forEachIndexed { index, selfieBody ->
            val start = selfieBody.textRange.startOffset
            var end = selfieBody.textRange.endOffset
            if (index != snapshots.lastIndex) end-- // Keep line ending before next snapshot
            if (start < end) result.add(FoldingDescriptor(selfieBody, TextRange(start, end)))
        }
        return result.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String = "..."

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false
}