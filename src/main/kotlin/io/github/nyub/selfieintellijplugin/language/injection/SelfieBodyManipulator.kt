package io.github.nyub.selfieintellijplugin.language.injection

import com.intellij.openapi.util.TextRange
import com.intellij.psi.AbstractElementManipulator
import io.github.nyub.selfieintellijplugin.language.psi.SelfieBody
import io.github.nyub.selfieintellijplugin.language.psi.SelfieBodyImpl
import io.github.nyub.selfieintellijplugin.language.psi.SelfieTypes

/**
 * **NB:** Since snapshots are not really meant to be edited by hand, we do not really need rich manipulation.
 *
 * However, implementing ElementManipulator allows richer actions on injected snapshots beyond editions, such as regex checking or markdown preview
 */
class SelfieBodyManipulator : AbstractElementManipulator<SelfieBody>() {
    override fun handleContentChange(element: SelfieBody, range: TextRange, newContent: String?): SelfieBody {
        if (element is SelfieBodyImpl && newContent != null) {
            val node = element.node
            node.removeChild(node.lastChildNode)
            node.addLeaf(SelfieTypes.SNAPSHOT_CONTENT, newContent, null)
        }
        return element
    }
}