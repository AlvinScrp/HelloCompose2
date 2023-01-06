package com.a.hellocompose2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Sample1() {

    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    Column {
        AnimatedVisibility(
            visibleState = state,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column {
                Text(text = "Hello, world!")
                // Fade in/out the background and the foreground.
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray)
                ) {
                    Box(
                        Modifier
                            .align(Alignment.Center)
                            .animateEnterExit(
                                // Slide in/out the inner box.
                                enter = slideInVertically(),
                                exit = slideOutVertically()
                            )
                            .sizeIn(minWidth = 256.dp, minHeight = 64.dp)
                            .background(Color.Red)
                    ) {
                        // Content of the notification…
                    }
                }
            }
        }

        Text(
            text = when {
                state.isIdle && state.currentState -> "Visible"
                !state.isIdle && state.currentState -> "Disappearing"
                state.isIdle && !state.currentState -> "Invisible"
                else -> "Appearing"
            }
        )
    }
}

@Preview
@Composable
fun Sample1Preview() {
    MaterialTheme {
        Surface {
            Sample1()
        }
    }
}