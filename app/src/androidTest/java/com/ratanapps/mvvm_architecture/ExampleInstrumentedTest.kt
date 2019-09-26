package com.ratanapps.mvvm_architecture

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented mvvm_architecture, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under mvvm_architecture.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.ratanapps.mvvm_architecture", appContext.packageName)
    }
}
