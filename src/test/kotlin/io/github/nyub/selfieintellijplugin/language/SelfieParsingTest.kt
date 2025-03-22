package io.github.nyub.selfieintellijplugin.language

import com.intellij.testFramework.ParsingTestCase
import kotlin.io.path.Path
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.nameWithoutExtension
import kotlin.reflect.full.functions

internal class SelfieParsingTest : ParsingTestCase("", "ss", SelfieParserDefinition()) {

    fun testHeaderPath() = doTest(true)
    fun testMissingContent() = doTest(true)
    fun testMissingHeader() = doTest(true)
    fun testMultipleSnapshots() = doTest(true)
    fun testSingleLineSnapshot() = doTest(true)

    /**
     * Ensure there is no test file without associated test
     */
    fun testNoMissingTest() {
        val testCases = testDataPath.let(::Path).listDirectoryEntries("*.ss").map { it.nameWithoutExtension }.sorted()
        val testMethods = SelfieParsingTest::class.functions
            .filter { it.name.startsWith("test") }
            .map { it.name.removePrefix("test") }
            .filterNot { it == "NoMissingTest" }
            .sorted()
        assertEquals(testCases, testMethods)
    }

    override fun getTestDataPath(): String = "src/test/testData"
}