package io.github.nyub.selfieintellijplugin.language.psi

import com.intellij.psi.util.parents

object SelfiePsiExtensions {
    @JvmStatic
    val SelfieBody.parentSnapshot: SelfieSnapshot?
        get() =
            parents(false).firstNotNullOfOrNull { it as? SelfieSnapshot }

    @JvmStatic
    val SelfieSnapshot.facet: String?
        get() =
            header.facet?.text?.substringAfter('[')?.substringBefore(']')

    @JvmStatic
    val SelfieSnapshot.path: String
        get() =
            header.headerPath.text

    @JvmStatic
    val SelfieSnapshot.pathAndFacet: String
        get() {
            return if (facet == null) path else "$path [$facet]"
        }
}