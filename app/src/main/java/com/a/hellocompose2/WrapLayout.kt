package com.a.hellocompose2

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WrapLayout(
    modifier: Modifier,
    horizontalSpace: Dp = 0.dp,
    verticalSpace: Dp = 0.dp,
    content: @Composable () -> Unit

) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { measurable -> measurable.measure(constraints) }
        var x = 0;
        var y = 0;

        layout(constraints.maxWidth, constraints.maxHeight) {
            var maxWidth = constraints.maxWidth

            placeables.forEach { placeable ->
                val width = placeable.width
                val newX = x + horizontalSpace.toPx() + width
                val canPlace = newX <= maxWidth
                if (!canPlace) {
                    x = 0
                    y += (placeable.height + verticalSpace.value).toInt()
                }
                placeable.placeRelative(x = x, y = y)
                x += (horizontalSpace.toPx() + width).toInt()
            }
        }
    }

}

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
        horizontalSpace = 10.dp,
        verticalSpace = 10.dp
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
