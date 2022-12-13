package com.a.wanandroid.component

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WrapLayout(
    modifier: Modifier,
    content: @Composable () -> Unit

) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { measurable -> measurable.measure(constraints) }
        var x = 0;
        var y = 0;

        Log.d("alvin", "maxWidth:${constraints.maxWidth}")

        var maxWidth = constraints.maxWidth

        val positionMap = mutableMapOf<Placeable, Position>()

        var height = 0

        placeables.forEach { placeable ->
            val width = placeable.width
            val newX = x + width
            val canPlace = newX <= maxWidth
            if (!canPlace) {
                x = 0
                y += placeable.height
            }
            positionMap[placeable] = Position(x, y)
            x += width
            height = y+placeable.height
        }

        layout(constraints.maxWidth, height) {

            placeables.forEach { placeable ->
                positionMap[placeable]?.let { position ->
                    placeable.placeRelative(
                        position.x,
                        position.y
                    )
                }
            }

        }
    }

}

data class Position(val x: Int, val y: Int)

@Preview
@Composable
fun PreviewWrapLayout() {
    val list = listOf(
        "死亡不是失去生命",
        ",",
        "而是走出了时间",
        ".",
        "他们说的话",
        ",",
        "我连标点符号都不信",
        ",",
        "没有什么比时间更具有说服力了",
        ".",
        "我不怕死",
        ",",
        "一点都不怕",
        ",",
        "只怕再也不能看见你",
        "."
    )
    WrapLayout(
        modifier = Modifier.padding(5.dp),
    ) {
        list.forEach {
            Card(
                modifier = Modifier.border(
                    width = 1.dp,
                    shape = RoundedCornerShape(5.dp),
                    color = Color.Cyan
                )
            ) {
                Text(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp), text = it)
            }
        }
    }
}
