package io.github.nyub.selfieintellijplugin.language.injection

import com.intellij.json.JsonLanguage
import com.intellij.lang.Language
import com.intellij.testFramework.LightPlatformTestCase
import io.github.nyub.selfieintellijplugin.language.`is equal to`

class LanguageGuessTest : LightPlatformTestCase() {
    fun `test guess by extension happy path`() =
        guessLanguage("a.json", null) `is equal to` JsonLanguage.INSTANCE

    fun `test guess by extension multiple dots`() =
        guessLanguage("a.nothing.json", null) `is equal to` JsonLanguage.INSTANCE

    fun `test guess by facet happy path`() =
        guessLanguage("a", "json") `is equal to` JsonLanguage.INSTANCE

    fun `test guess by facet extension`() =
        guessLanguage("a", "a.json") `is equal to` JsonLanguage.INSTANCE

    fun `test guess by facet extension multiple dots`() =
        guessLanguage("a", "a.nothing.json") `is equal to` JsonLanguage.INSTANCE

    fun `test when both extension and facet specified, choose facet`() =
        guessLanguage("a.txt", "json") `is equal to` JsonLanguage.INSTANCE

    fun `test guess by extension table`() =
        guessLanguage("a.txt", null) `is equal to` Language.findLanguageByID("TEXT")!!

    fun `test plain language id`() =
        guessLanguage("json", null) `is equal to` JsonLanguage.INSTANCE

    fun `test further elements after extension`() =
        guessLanguage("a.json/else", null) `is equal to` null

    fun `test do not return ANY`() =
        guessLanguage("", null) `is equal to` null
}