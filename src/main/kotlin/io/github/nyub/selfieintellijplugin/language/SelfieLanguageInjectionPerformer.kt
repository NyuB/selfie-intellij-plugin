package io.github.nyub.selfieintellijplugin.language

import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.lang.injection.general.Injection
import com.intellij.lang.injection.general.LanguageInjectionPerformer
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class SelfieLanguageInjectionPerformer : LanguageInjectionPerformer {
    override fun isPrimary(): Boolean {
        return true
    }

    override fun performInjection(registrar: MultiHostRegistrar, injection: Injection, context: PsiElement): Boolean {
        val host = context as? SelfieBody ?: return false
        val language = injection.injectedLanguage ?: return false
        registrar.startInjecting(language)
        registrar.addPlace(null, null, host, TextRange(0, host.textLength))
        registrar.doneInjecting()
        return true
    }
}