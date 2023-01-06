package com.example.compiletest.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.Snapshot

fun test() {
    Log.d("alvin","11111")
    // 创建状态（主线开发）
    val state = mutableStateOf(1)

    // 创建快照（开分支）
    val snapshot = Snapshot.takeSnapshot()
    
    Snapshot.registerApplyObserver { anies, snapshot ->  }

    // 修改状态（主线修改状态）
    state.value = 2

    Log.d("alvin","state.value:${state.value}")// 打印2


    snapshot.enter {//进入快照（切换分支）
        // 读取快照状态（分支状态）
        Log.d("alvin","state.value:${state.value}")// 打印1
    }
    // snapshot.apply() 保存快照（下面print statr打印1）

    // 读取状态（主线状态）
    Log.d("alvin","state.value:${state.value}")// 打印2

    // 废弃快照（删除分支）
    snapshot.dispose()
}
