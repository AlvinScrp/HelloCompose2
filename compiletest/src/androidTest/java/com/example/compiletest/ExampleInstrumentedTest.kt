package com.example.compiletest

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.lang.IllegalStateException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.compiletest", appContext.packageName)
    }

    @Test
    fun snapshotTest() {
        // 创建可跟踪读写的可变状态，初始化值：0
        var state: Int by mutableStateOf(0)

        // 创建一个可写的快照环境
        val snapshot = Snapshot.takeMutableSnapshot(
            { state -> Log.d("alvin",Log.getStackTraceString(IllegalStateException("/* 状态被读取 */"))) },
            { state -> Log.d("alvin",Log.getStackTraceString(IllegalStateException("/* 状态被写入 */"))) }
        )
        Log.d("alvin","sdsdsdsdsd")
        // 进入快照环境
        snapshot.enter {
            // 修改状态的值
            state = 1
            assertEquals(1, state)
        }

        // 在快照提交前，快照环境外的状态值不会受到影响
        assertEquals(0, state)

        // 以原子的方式提交快照中所有对状态的修改，出现并发修改时提交可能会失败
        val result = snapshot.apply()
        assertEquals(true, result.succeeded)
        assertEquals(1, state)
    }

}