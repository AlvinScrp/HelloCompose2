package com.a.hellocompose2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val context = LocalContext.current
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
        Row {
            repeat(2) {
                log("repeat $it")
                Text(text = "Hello $it")
            }
        }
        Text2(messageProvide = { message.value })
        log(4)
//        ImageK()
//
//        Column {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .paint(painterResource(id = R.mipmap.imge_k))
//                    .fillMaxWidth(1f)
//                    .height(40.dp)
//                    .clickable { toast(context, "click 带背景图按钮") }) {
//                Text(
//                    "带背景图按钮",
//                    color = Color.Cyan,
//                    textAlign = TextAlign.Center,
//                    lineHeight = 40.sp,
//                )
//            }
//
//
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .horizontalScroll(rememberScrollState())
//            ) {
//
//                //1 纯文字按钮
//                TextButton(
//                    onClick = { toast(context, "click 1 纯文字按钮") }
//                ) {
//                    Text("TextButton")
//                }
//                Spacer(modifier = Modifier.width(5.dp))
//                //2 普通按钮
//                Button(onClick = { /*TODO*/ }) {
//                    Text(text = "Button")
//                }
//                Spacer(modifier = Modifier.width(5.dp))
//                // 3 带Icon的按钮
//                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.requiredWidth(120.dp)) {
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Icon(
//                            Icons.Filled.Favorite, contentDescription = "Favorite",
//                            modifier = Modifier.size(ButtonDefaults.IconSize)
//                        )
//                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                        Text(text = "IconButton")
//                    }
//                }
//            }
//            Row {
//                Spacer(modifier = Modifier.width(5.dp))
//                // 4 IconToggleButton
//                IconToggleButton(
//                    checked = true, onCheckedChange = {},
//                    modifier = Modifier.requiredWidth(130.dp)
//                ) {
//                    Text(text = "IconToggleButton")
//                }
//                Spacer(modifier = Modifier.width(5.dp))
//                //5 OutlinedButton
//                OutlinedButton(onClick = { /*TODO*/ }) {
//                    Text(text = "OutlinedButton")
//                }
//                Spacer(modifier = Modifier.width(5.dp))
//                // 6 RadioButton
//                RadioButton(selected = true, onClick = { /*TODO*/ })
//            }
//
//            //扩展悬浮按钮
//            ExtendedFloatingActionButton(
//                icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "") },
//                text = { Text("Like") },
//                onClick = { },
//            )
//        }
//
//
    }
}

/**
 * 修饰符使用示例
 */
@Composable
fun ImageK() {
    val context = LocalContext.current
    //添加一个图片
    Image(
        //填充内容
        painter = painterResource(id = R.mipmap.imge_k),
        contentDescription = "logo",
        contentScale = ContentScale.Crop,
        //尺寸及形状
        modifier = Modifier
            .background(Color.Blue)//设置蓝色背景
            .clickable { toast(context, "Image k click") }
            .padding(all = 12.dp)
            .size(150.dp)             //图像尺寸
            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(25.dp))       //圆形形状
            .border(3.dp, Color.Red, RoundedCornerShape(25.dp))//边框样式
    )
}

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = layout { measurable, constraints ->
    // Measure the composable
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        // Where the composable gets placed
        placeable.placeRelative(0, placeableY)
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