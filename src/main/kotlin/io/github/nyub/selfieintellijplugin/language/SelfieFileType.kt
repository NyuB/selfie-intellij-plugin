package io.github.nyub.selfieintellijplugin.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object SelfieFileType : LanguageFileType(SelfieLanguage) {
    override fun getName(): String {
        return "selfie"
    }

    override fun getDescription(): String {
        return "Selfie snapshot file"
    }

    override fun getDefaultExtension(): String {
        return "ss"
    }

    override fun getIcon(): Icon {
        return SelfieLanguage.ICON
    }
}