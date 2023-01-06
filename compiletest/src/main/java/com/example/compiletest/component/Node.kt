package com.example.compiletest.component

import androidx.compose.runtime.AbstractApplier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.ReusableComposeNode
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
//
//fun main() {
//    val composer = Recomposer(Dispatchers.Main)
//
////    GlobalSnapshotManager.ensureStarted() // 监听
////    val mainScope = MainScope()
////    mainScope.launch(DefaultChoreographerFrameClock) {
////        composer.runRecomposeAndApplyChanges() // Choreographer Frame回调时开始重组
////    }
//
//    val rootNode = Node.RootNode()
//    Composition(NodeApplier(rootNode), composer).apply {
//        setContent {
//            Content()
//        }
//    }
//}

@Preview
@Composable
fun MainPreview(){
    Content()
}

@Composable
fun Content() {
    var state by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(3000)
        state = false
    }
    if (state) {
        Node1()
    }
    Node2()
}



sealed class Node {
    val children = mutableListOf<Node>()

    class RootNode : Node() {
//        override fun toString(): String {
//            return rootNodeToString()
//        }
    }

    data class Node1(
        var name: String = "",
    ) : Node()

    data class Node2(
        var name: String = "",
    ) : Node()
}

class NodeApplier(node: Node) : AbstractApplier<Node>(node) {
    override fun insertBottomUp(index: Int, instance: Node) {
        val realIndex = current.children.size - index
        current.children.add(realIndex, instance) // 插入节点
    }

    override fun insertTopDown(index: Int, instance: Node) {
        current.children.add(index, instance) // 插入节点
    }

    override fun move(from: Int, to: Int, count: Int) {
        current.children.move(from, to, count) // 更新节点
    }

    override fun onClear() {
    }

    override fun remove(index: Int, count: Int) {
        current.children.remove(index, count) // 移除节点
    }

}

@Composable
private fun Node1(name: String = "node1") {
    ReusableComposeNode<Node.Node1, NodeApplier>(
        factory = {
            Node.Node1()
        },
        update = {
            set(name) { this.name = it }
        }
    )
}

@Composable
private fun Node2(name: String = "node2") {
    ReusableComposeNode<Node.Node2, NodeApplier>(
        factory = {
            Node.Node2()
        },
        update = {
            set(name) { this.name = it }
        }
    )
}