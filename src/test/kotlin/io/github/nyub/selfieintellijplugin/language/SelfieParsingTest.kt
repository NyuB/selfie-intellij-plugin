package io.github.nyub.selfieintellijplugin.language

import com.intellij.testFramework.ParsingTestCase
import io.github.nyub.selfieintellijplugin.language.SelfieParserDefinition
import kotlin.io.path.Path
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.nameWithoutExtension
import kotlin.reflect.full.functions

internal class SelfieParsingTest : ParsingTestCase("", "ss", SelfieParserDefinition()) {

    fun testSingleLineSnapshot() = doTest(true)
    fun testMissingHeader() = doTest(true)
    fun testEmptySnapshot() = doTest(true)
    fun testMultipleSnapshots() = doTest(true)

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