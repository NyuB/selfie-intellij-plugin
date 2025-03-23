package io.github.nyub.selfieintellijplugin.language.injection

import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.testFramework.fixtures.assertInjectedLanguage

class LanguageInjectionTest : BasePlatformTestCase() {
    fun `test automatically inject detected language`() {
        myFixture.configureByFile("/JsonSnapshot.ss")
        myFixture.assertInjectedLanguage("JSON", "{")
        myFixture.editor.caretModel.moveToOffset(myFixture.file.text.indexOf('{'))
        myFixture.availableIntentions offers "Edit JSON Fragment"
    }

    fun `test offers dedicated intentions for the injected language`() {
        myFixture.configureByFile("/RegexpSnapshot.ss")
        myFixture.availableIntentions offers "Edit RegExp Fragment"
        myFixture.availableIntentions offers "Check RegExp" // Not available for other languages of course ;)
    }

    fun `test offers manual injection when snapshot language cannot be detected`() {
        myFixture.configureByFile("/Unknown.ss")
        myFixture.availableIntentions offers "Inject language or reference"
    }

    private operator fun IntentionAction.invoke() {
        invoke(myFixture.project, myFixture.editor, myFixture.file)
    }

    private infix fun Iterable<IntentionAction>.offers(actionText: String): IntentionAction {
        assertContainsElements(
            this.map { it.text },
            listOf(actionText)
        )
        return this.first { it.text == actionText }
    }

    override fun getTestDataPath(): String = "src/test/testData/injection"
}