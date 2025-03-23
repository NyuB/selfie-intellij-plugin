package io.github.nyub.selfieintellijplugin.language

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import io.github.nyub.selfieintellijplugin.language.psi.SelfieLexerAdapter
import io.github.nyub.selfieintellijplugin.language.psi.SelfieTypes

class SelfieSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        @JvmField
        val HEADERS: TextAttributesKey =
            createTextAttributesKey("SELFIE_SNAPSHOT_HEADER", DefaultLanguageHighlighterColors.OPERATION_SIGN)

        @JvmField
        val CONTENT: TextAttributesKey =
            createTextAttributesKey("SELFIE_SNAPSHOT_CONTENT", DefaultLanguageHighlighterColors.STRING)

        val HEADER_KEYS = arrayOf(HEADERS)
        val CONTENT_KEYS = arrayOf(CONTENT)

    }

    override fun getHighlightingLexer(): Lexer = SelfieLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            SelfieTypes.HEADER -> HEADER_KEYS
            SelfieTypes.SNAPSHOT_CONTENT -> CONTENT_KEYS
            else -> arrayOf()
        }
    }
}