package com.example.compiletest.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot

@Composable
fun IncrementCount() {
    var count by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = count, block = {
        Log.d("alvin","hehehei")
    })
    Column {
        IncrementText{
            "count:${count}"
        }
        Button(onClick = {
//            count++
            snapshotTest()
        }) {
            Text(text = "increment")
        }
    }
}

@Composable
fun IncrementText(textProvider:()->String){
    Text(text = textProvider.invoke())
}

fun snapshotTest() {
    // 创建可跟踪读写的可变状态，初始化值：0
    var state: Int by mutableStateOf(0)

    // 创建一个可写的快照环境
    val snapshot = Snapshot.takeMutableSnapshot(
        { state -> Log.d("alvin","/* 状态被读取 */") },
        { state -> Log.d("alvin","/* 状态被写入 */") }
    )
    Log.d("alvin","sdsdsdsdsd")
    // 进入快照环境
    snapshot.enter {
        // 修改状态的值
        state = 1
        Log.d("alvin","snapshot.enter 写入并读取状态值：state:${state} equal 1 ")

    }

    // 在快照提交前，快照环境外的状态值不会受到影响
    Log.d("alvin","在快照提交前，快照环境外的状态值不会受到影响：state:${state} equal 0  ")

    state = 3
    Log.d("alvin","snapshot.apply后：result.succeeded：,state:${state} equal 3  ")

    // 以原子的方式提交快照中所有对状态的修改，出现并发修改时提交可能会失败
    val result = snapshot.apply()
    Log.d("alvin","snapshot.apply后：result.succeeded：${result.succeeded},state:${state} equal 1  ")

}