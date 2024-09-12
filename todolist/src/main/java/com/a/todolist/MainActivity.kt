package com.a.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
                    color = MaterialTheme.colorScheme.background
                ) {
                    Nav.setupNav(vm = vm)
                }
            }
        }
    }
}