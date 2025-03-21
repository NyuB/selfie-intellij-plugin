@file:JvmName("LanguageGuess")
package io.github.nyub.selfieintellijplugin.language

import com.intellij.lang.Language

fun guessLanguage(selfieHeader: String): Language? {
    val regex = Regex("([.][^./\\s\\t\\n\\r]+)?\\s*(\\[[^]]+])?$")
    val find = regex.find(selfieHeader) ?: return null
    val extension = find.groups[1]?.value?.substringAfter('.')
    val facet = find.groups[2]?.value?.substringAfter('[')?.substringBefore(']')
    val idToSearch = facet ?: extension ?: return null
    val mappedId = languageExtensionTable.firstNotNullOfOrNull { (id, ext) ->
        id.takeIf { ext.contains(idToSearch) }
    } ?: idToSearch
    return Language.getRegisteredLanguages().firstOrNull {
        it.id.lowercase() == mappedId.lowercase()
    }
}

private val languageExtensionTable = listOf(
    // Keep this list sorted to ease reading
    "kotlin" to setOf("kt"),
    "markdown" to setOf("md"),
    "python" to setOf("py", "python2", "python3"),
    "relax-ng" to setOf("rng"),
    "ruby" to setOf("rb"),
    "rust" to setOf("rs"),
    "shell script" to setOf("bash", "sh", "zsh"),
    "text" to setOf("txt"),
    "yaml" to setOf("yml"),
)