package io.github.nyub.selfieintellijplugin.language.folding

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class FoldingTest : BasePlatformTestCase() {
    fun test() {
        myFixture.testFolding("${testDataPath}/Snapshot.ss")
    }

    override fun getTestDataPath(): String {
        return "src/test/testData/folding"
    }
}