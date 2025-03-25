package io.github.nyub.selfieintellijplugin.language.psi

import com.intellij.testFramework.ParsingTestCase
import kotlin.io.path.Path
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.nameWithoutExtension
import kotlin.reflect.full.functions

internal class ParsingTest : ParsingTestCase("parsing", "ss", SelfieParserDefinition()) {

    fun testHeaderPath() = doTest(true)
    fun testMissingContent() = doTest(true)
    fun testMissingHeader() = doTest(true)
    fun testMultipleSnapshots() = doTest(true)
    fun testSingleLineSnapshot() = doTest(true)
    fun testFacet() = doTest(true)
    fun testEndOfFile() = doTest(true)

    /**
     * Ensure there is no test file without associated test
     */
    fun testNoMissingTest() {
        val testCases = myFullDataPath.let(::Path).listDirectoryEntries("*.ss").map { it.nameWithoutExtension }.sorted()
        val testMethods = ParsingTest::class.functions
            .filter { it.name.startsWith("test") }
            .map { it.name.removePrefix("test") }
            .filterNot { it == "NoMissingTest" }
            .sorted()
        assertEquals(testCases, testMethods)
    }

    override fun getTestDataPath(): String = "src/test/testData"
}