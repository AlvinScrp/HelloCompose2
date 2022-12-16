package com.a.wanandroid.ui.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun HelloCompose2Theme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography,
        shapes = Shapes
    ) {
        CompositionLocalProvider(LocalIndication provides  NoIndication,content = content)
    }
}

object NoIndication : Indication {
    private object NoIndicationInstance : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return NoIndicationInstance
    }

}

@Composable
fun setStatusBarColor(color: Color = Color.White) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = color)
    }
}