package io.github.nyub.selfieintellijplugin.language.injection

import com.intellij.lang.injection.general.Injection
import com.intellij.lang.injection.general.LanguageInjectionContributor
import com.intellij.lang.injection.general.SimpleInjection
import com.intellij.psi.PsiElement
import io.github.nyub.selfieintellijplugin.language.psi.SelfieBody
import io.github.nyub.selfieintellijplugin.language.psi.SelfiePsiExtensions.facet
import io.github.nyub.selfieintellijplugin.language.psi.SelfiePsiExtensions.parentSnapshot
import io.github.nyub.selfieintellijplugin.language.psi.SelfiePsiExtensions.path

class SelfieLanguageInjectionContributor : LanguageInjectionContributor {
    override fun getInjection(context: PsiElement): Injection? {
        if (context is SelfieBody) {
            val headerPath = context.parentSnapshot?.path ?: return null
            val facet = context.parentSnapshot?.facet
            return guessLanguage(headerPath, facet)?.let { SimpleInjection(it, "", "", null) }
        }
        return null
    }
}