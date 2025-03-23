package io.github.nyub.selfieintellijplugin.language.psi

import com.intellij.psi.tree.IElementType
import io.github.nyub.selfieintellijplugin.language.SelfieLanguage
import org.jetbrains.annotations.NonNls

class SelfieElementType(@NonNls debugName: String) : IElementType(debugName, SelfieLanguage)
