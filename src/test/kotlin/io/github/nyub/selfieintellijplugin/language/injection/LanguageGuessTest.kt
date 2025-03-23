package io.github.nyub.selfieintellijplugin.language.injection

import com.intellij.json.JsonLanguage
import com.intellij.lang.Language
import com.intellij.testFramework.LightPlatformTestCase

class LanguageGuessTest : LightPlatformTestCase() {
    fun `test guess by extension happy path`() {
        guessLanguage("a.json", null) isEqualTo JsonLanguage.INSTANCE
    }

    fun `test guess by extension multiple dots`() {
        guessLanguage("a.nothing.json", null) isEqualTo JsonLanguage.INSTANCE
    }

    fun `test guess by facet happy path`() {
        guessLanguage("a", "json") isEqualTo JsonLanguage.INSTANCE
    }

    fun `test guess by facet extension`() {
        guessLanguage("a", "a.json") isEqualTo JsonLanguage.INSTANCE
    }

    fun `test guess by facet extension multiple dots`() {
        guessLanguage("a", "a.nothing.json") isEqualTo JsonLanguage.INSTANCE
    }

    fun `test when both extension and facet specified, choose facet`() {
        guessLanguage("a.txt", "json") isEqualTo JsonLanguage.INSTANCE
    }

    fun `test guess by extension table`() {
        guessLanguage("a.txt", null) isEqualTo Language.findLanguageByID("TEXT")!!
    }

    fun `test plain language id`() {
        guessLanguage("json", null) isEqualTo JsonLanguage.INSTANCE
    }

    fun `test further elements after extension`() {
        guessLanguage("a.json/else", null) isEqualTo null
    }

    fun `test do not return ANY`() {
        guessLanguage("", null) isEqualTo null
    }

    private infix fun <T> T.isEqualTo(other: T) {
        assertEquals(other, this)
    }
}