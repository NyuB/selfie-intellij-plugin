package io.github.nyub.selfieintellijplugin.language

import com.intellij.lang.Language
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object SelfieLanguage : Language("Selfie") {
    val ICON: Icon = IconLoader.getIcon("/icons/multiplatformMobile.svg", SelfieLanguage::class.java)
    private fun readResolve(): Any = SelfieLanguage
}