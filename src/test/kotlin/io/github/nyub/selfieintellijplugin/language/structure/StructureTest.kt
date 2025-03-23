package io.github.nyub.selfieintellijplugin.language.structure

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class StructureTest : BasePlatformTestCase() {
    fun `test three snapshots`() {
        myFixture.configureByFile("/parsing/MultipleSnapshots.ss")
        myFixture.testStructureView {
            it.tree.model.getChildCount(it.tree.model.root) `is equal to` 3
        }
    }

    private infix fun <T> T.`is equal to`(other: T) {
        assertEquals(other, this)
    }

    override fun getTestDataPath(): String {
        return "src/test/testData"
    }
}