package io.github.nyub.selfieintellijplugin.language

import com.intellij.lexer.FlexAdapter

class SelfieLexerAdapter : FlexAdapter(SelfieLexer(null))