// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package io.github.nyub.selfieintellijplugin.language.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import io.github.nyub.selfieintellijplugin.language.psi.SelfieTypes;

%%

%class SelfieLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CONTENT_STRING_NEWLINE=\r\n|[\r\n]
CONTENT_STRING_NON_EMPTY_LINE=[^╔\n\r][^\n\r]*\n?
CONTENT_STRING_LINE={CONTENT_STRING_NEWLINE} | {CONTENT_STRING_NON_EMPTY_LINE}
CONTENT_STRING={CONTENT_STRING_LINE}*
PATH_ELEMENT=[^═╗/\n\r\[]*[^═╗/\n\r\s\[]
PATH_SEPARATOR=[/]
TOP_LEFT_CORNER=╔═\s
TOP_RIGHT_CORNER=\s═╗
LEFT_BRACKET=\s*\[
RIGHT_BRACKET=]
FACET_STRING=[^\r\n\]]*

%state SNAPSHOT_CONTENT SNAPSHOT_START FACET

%%
<YYINITIAL>        {TOP_LEFT_CORNER}        { return SelfieTypes.LEFT_CORNER; }
<YYINITIAL>        {PATH_ELEMENT}           { return SelfieTypes.PATH_ELEMENT; }
<YYINITIAL>        {PATH_SEPARATOR}         { return SelfieTypes.PATH_SEPARATOR; }
<YYINITIAL>        {TOP_RIGHT_CORNER}       { yybegin(SNAPSHOT_START); return SelfieTypes.RIGHT_CORNER; }
<YYINITIAL>        {LEFT_BRACKET}           { yybegin(FACET); return SelfieTypes.LEFT_BRACKET; }
<FACET>            {FACET_STRING}           { return SelfieTypes.FACET_CONTENT; }
<FACET>            {RIGHT_BRACKET}          { yybegin(YYINITIAL); return SelfieTypes.RIGHT_BRACKET; }
<SNAPSHOT_CONTENT> {CONTENT_STRING}         { yybegin(YYINITIAL); return SelfieTypes.SNAPSHOT_CONTENT; }
<SNAPSHOT_START>   {CONTENT_STRING_NEWLINE} { yybegin(SNAPSHOT_CONTENT); return SelfieTypes.NEW_LINE; }

                   [^]                      { return TokenType.BAD_CHARACTER; }