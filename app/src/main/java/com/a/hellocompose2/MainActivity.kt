package com.a.hellocompose2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a.hellocompose2.ui.theme.HelloCompose2Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    private val vm by lazy { GreetingViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloCompose2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android", vm)
                }
            }
        }
    }
}

class GreetingViewModel : ViewModel() {
    fun onValueChange(text: String) {
        _textLiveData.postValue(text)
    }

    private val _textLiveData = MutableLiveData("XXXXX")
    val textLiveData: LiveData<String> = _textLiveData


}


@Composable
fun Greeting(name: String, vm: GreetingViewModel) {
//    Text(text = "Hello $name!")
    val message = remember {
        mutableStateOf("Init???")
    }

    LaunchedEffect(key1 = Unit) {
        delay(1000L)
        log("LaunchedEffect")
        message.value = "after LaunchedEffect"
    }
    Column {
//        var text by remember { mutableStateOf(name) }
        var text = vm.textLiveData.observeAsState("")
        OutlinedTextField(
            value = text.value,
            onValueChange = { vm.onValueChange(it) },
            label = { Text("Name") })
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .width(30.dp)
        )
        Text(text = "Hello ${text.value}!")
        log(1)
        Column {
            repeat(4) {
                log("repeat $it")
                Text(text = "Hello $it")
            }
        }
        Text2(messageProvide = { message.value })
        log(4)
    }


}

@Composable
fun Text2(messageProvide: () -> String) {
    Text(text = messageProvide())
}

private fun log(any: Any) {
    Log.d("MainActivity", any.toString())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloCompose2Theme {
        Greeting("Android", GreetingViewModel())
    }
}