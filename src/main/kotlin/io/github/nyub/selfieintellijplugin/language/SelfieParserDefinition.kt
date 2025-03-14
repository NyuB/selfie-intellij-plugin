package io.github.nyub.selfieintellijplugin.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class SelfieParserDefinition : ParserDefinition {
    companion object {
        @JvmField
        val FILE = IFileElementType(SelfieLanguage)
    }

    override fun createLexer(project: Project): Lexer = SelfieLexerAdapter()

    override fun createParser(project: Project): PsiParser = SelfieParser()

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun getCommentTokens(): TokenSet = TokenSet.EMPTY

    override fun getStringLiteralElements(): TokenSet {
        return SelfieTokenSets.SNAPSHOT_CONTENT
    }

    override fun createElement(node: ASTNode): PsiElement {
        return SelfieTypes.Factory.createElement(node)
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile = SelfieFile(viewProvider)
}