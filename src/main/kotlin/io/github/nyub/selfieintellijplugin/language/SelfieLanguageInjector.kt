package io.github.nyub.selfieintellijplugin.language

import com.intellij.lang.injection.general.Injection
import com.intellij.lang.injection.general.LanguageInjectionContributor
import com.intellij.psi.PsiElement

class SelfieLanguageInjector : LanguageInjectionContributor {
    override fun getInjection(context: PsiElement): Injection? {
        return null
    }
}