package io.github.nyub.selfieintellijplugin.language

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class SelfieFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, SelfieLanguage) {
    override fun getFileType(): FileType = SelfieFileType
    override fun toString(): String {
        return "Selfie file"
    }
}