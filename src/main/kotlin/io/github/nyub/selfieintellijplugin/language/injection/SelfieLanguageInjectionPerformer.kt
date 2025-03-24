package io.github.nyub.selfieintellijplugin.language.injection

import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.lang.injection.general.Injection
import com.intellij.lang.injection.general.LanguageInjectionPerformer
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import io.github.nyub.selfieintellijplugin.language.psi.SelfieBody

class SelfieLanguageInjectionPerformer : LanguageInjectionPerformer {
    override fun isPrimary(): Boolean = true

    override fun performInjection(registrar: MultiHostRegistrar, injection: Injection, context: PsiElement): Boolean {
        val host = context as? SelfieBody ?: return false
        val language = injection.injectedLanguage ?: return false

        with(registrar) {
            startInjecting(language)
            addPlace(null, null, host, TextRange(0, host.textLength))
            doneInjecting()
        }

        return true
    }
}