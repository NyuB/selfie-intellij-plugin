package io.github.nyub.selfieintellijplugin.language

import com.intellij.lang.injection.general.Injection
import com.intellij.lang.injection.general.LanguageInjectionContributor
import com.intellij.lang.injection.general.SimpleInjection
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parents

class SelfieLanguageInjector : LanguageInjectionContributor {
    override fun getInjection(context: PsiElement): Injection? {
        if (context is SelfieBody) {
            return context.headerPath?.let(::guessLanguage)?.let { SimpleInjection(it, "", "", null) }
        }
        return null
    }

    private val SelfieBody.headerPath: String?
        get() {
            val snapshot = this.parents(false).firstNotNullOfOrNull { it as? SelfieSnapshot }
            return snapshot?.header?.children?.joinToString(separator = "") { it.text }
        }
}