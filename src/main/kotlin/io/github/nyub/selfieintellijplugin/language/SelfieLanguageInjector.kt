package io.github.nyub.selfieintellijplugin.language

import com.intellij.lang.injection.general.Injection
import com.intellij.lang.injection.general.LanguageInjectionContributor
import com.intellij.lang.injection.general.SimpleInjection
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parents
import io.github.nyub.selfieintellijplugin.language.psi.SelfieBody
import io.github.nyub.selfieintellijplugin.language.psi.SelfieSnapshot

class SelfieLanguageInjector : LanguageInjectionContributor {
    override fun getInjection(context: PsiElement): Injection? {
        if (context is SelfieBody) {
            val headerPath = context.headerPath ?: return null
            val facet = context.facet
            return guessLanguage(headerPath, facet)?.let { SimpleInjection(it, "", "", null) }
        }
        return null
    }

    private val SelfieBody.headerPath: String?
        get() = snapshot?.header?.headerPath?.text

    private val SelfieBody.facet: String?
        get() =
            snapshot?.header?.facet?.text?.substringAfter("[")?.substringBefore("]")

    private val SelfieBody.snapshot: SelfieSnapshot?
        get() = parents(false).firstNotNullOfOrNull { it as? SelfieSnapshot }

}