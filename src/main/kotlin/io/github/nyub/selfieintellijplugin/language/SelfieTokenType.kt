package io.github.nyub.selfieintellijplugin.language

import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls

class SelfieTokenType(@NonNls debugName: String) : IElementType(debugName, SelfieLanguage) {
    override fun toString(): String = "SelfieTokenType.${super.toString()}"
}