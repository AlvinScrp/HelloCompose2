package com.a.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.a.todolist.nav.Nav
import com.a.todolist.ui.theme.HelloCompose2Theme
import com.a.todolist.ui.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    private val vm :MainViewModel by viewModels (  )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloCompose2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Nav.setupNav(vm = vm)
                }
            }
        }
    }
}