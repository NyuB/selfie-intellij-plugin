package io.github.nyub.selfieintellijplugin.language.psi

import com.intellij.lexer.FlexAdapter

class SelfieLexerAdapter : FlexAdapter(SelfieLexer(null))