@file:JvmName("LanguageGuess")

package io.github.nyub.selfieintellijplugin.language

import com.intellij.lang.Language

fun guessLanguage(path: String, facet: String?): Language? {
    val pathOrPathExtension = path
        .substringAfterLast('/')
        .substringAfterLast('.')
    val facetOrFacetExtension = facet
        ?.substringAfterLast(".")
    val idToSearch = facetOrFacetExtension ?: pathOrPathExtension
    val mappedId = languageExtensionTable.firstNotNullOfOrNull { (id, ext) ->
        id.takeIf { ext.contains(idToSearch) }
    } ?: idToSearch

    return Language.getRegisteredLanguages().firstOrNull {
        it.id.lowercase() == mappedId.lowercase()
    }?.takeIf { it != Language.ANY }
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