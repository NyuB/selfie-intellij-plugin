// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package io.github.nyub.selfieintellijplugin.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import io.github.nyub.selfieintellijplugin.language.SelfieTypes;

%%

%class SelfieLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CONTENT_STRING=((([^╔\n\r][^\n]*)\n?)|\n)*
HEADER=[^═╗\n\r]+
TOP_LEFT_CORNER=╔═
TOP_RIGHT_CORNER=═╗

%state SNAPSHOT_CONTENT

%%

{TOP_LEFT_CORNER} {HEADER} {TOP_RIGHT_CORNER}\n? { yybegin(SNAPSHOT_CONTENT); return SelfieTypes.HEADER; }
<SNAPSHOT_CONTENT> {CONTENT_STRING}              { yybegin(YYINITIAL); return SelfieTypes.SNAPSHOT_CONTENT; }
[^]                                              { return TokenType.BAD_CHARACTER; }