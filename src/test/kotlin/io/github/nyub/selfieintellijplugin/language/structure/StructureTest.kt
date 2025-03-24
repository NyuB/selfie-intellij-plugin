package io.github.nyub.selfieintellijplugin.language.structure

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import io.github.nyub.selfieintellijplugin.language.`is equal to`

class StructureTest : BasePlatformTestCase() {
    fun `test three snapshots`() {
        myFixture.configureByFile("/parsing/MultipleSnapshots.ss")
        myFixture.testStructureView {
            it.tree.model.getChildCount(it.tree.model.root) `is equal to` 3
        }
    }

    override fun getTestDataPath(): String = "src/test/testData"
}