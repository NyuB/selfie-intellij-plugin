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
PATH_ELEMENT=[^═╗/\n\r]*[^═╗/\n\r\s]
PATH_SEPARATOR=[/]
TOP_LEFT_CORNER=╔═\s
TOP_RIGHT_CORNER=\s═╗\n

%state SNAPSHOT_CONTENT

%%
<YYINITIAL>        {TOP_LEFT_CORNER}       { return SelfieTypes.LEFT_CORNER; }
<YYINITIAL>        {PATH_ELEMENT}          { return SelfieTypes.PATH_ELEMENT; }
<YYINITIAL>        {PATH_SEPARATOR}        { return SelfieTypes.PATH_SEPARATOR; }
<YYINITIAL>        {TOP_RIGHT_CORNER}      { yybegin(SNAPSHOT_CONTENT); return SelfieTypes.RIGHT_CORNER; }
<SNAPSHOT_CONTENT> {CONTENT_STRING}        { yybegin(YYINITIAL); return SelfieTypes.SNAPSHOT_CONTENT; }

                   [^]                     { return TokenType.BAD_CHARACTER; }