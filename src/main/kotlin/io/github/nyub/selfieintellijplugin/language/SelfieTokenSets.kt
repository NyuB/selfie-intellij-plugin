package io.github.nyub.selfieintellijplugin.language

import com.intellij.psi.tree.TokenSet

interface SelfieTokenSets {
    companion object {
        @JvmField
        val SNAPSHOT_CONTENT = TokenSet.create(SelfieTypes.SNAPSHOT_CONTENT)

        @JvmField
        val HEADER = TokenSet.create(SelfieTypes.HEADER)
    }
}