package io.github.nyub.selfieintellijplugin.language

import junit.framework.TestCase.assertEquals

infix fun <T> T.`is equal to`(other: T) = assertEquals(other, this)