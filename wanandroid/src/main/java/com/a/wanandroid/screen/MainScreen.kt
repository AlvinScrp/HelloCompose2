package com.a.wanandroid.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.a.wanandroid.nav.BottomItem
import com.a.wanandroid.nav.RouteData
import com.a.wanandroid.screen.main.MainIndexScreen
import com.a.wanandroid.screen.main.MainProjectScreen
import com.a.wanandroid.screen.main.MainWxArticleScreen
import com.a.wanandroid.ui.theme.setStatusBarColor

@Composable
fun MainScreen() {
    setStatusBarColor()
    val navCtrl = rememberNavController()
    val navEntry = navCtrl.currentBackStackEntryAsState()
    val curRoute = navEntry.value?.destination?.route

    Scaffold(
        backgroundColor = Color.White,
        topBar = { MainAppBar(curRoute) },
        bottomBar = {
            MainBottom(curRoute, onItemClick = { navigatorTo(navCtrl, it.route) })
        },
    ) {
        NavHost(
            navController = navCtrl,
            startDestination = RouteData.main_index,
            Modifier.padding(it)
        ) {
            composable(RouteData.main_index) {
                MainIndexScreen()
            }
            composable(RouteData.main_wxarticle) {
                MainWxArticleScreen()
            }
            composable(RouteData.main_project) { MainProjectScreen() }
        }
    }
}

@Composable
fun MainAppBar(curRoute: String?) {
    var item = RouteData.mainBottomItems.firstOrNull { it.route == curRoute }
    Column(
        modifier = Modifier
            .background(Color.White)
            .height(56.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(text = "${item?.text}")
    }

}

@Composable
fun MainBottom(curRoute: String?, onItemClick: (BottomItem) -> Unit) {
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        RouteData.mainBottomItems.forEach { item ->
            BottomNavigationItem(
                selected = curRoute == item.route,
                selectedContentColor = Color(0xFF1e80ff),
                unselectedContentColor = Color(0xFF616161),
                onClick = { onItemClick.invoke(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.resImg),
                        contentDescription = item.text,
                        modifier = Modifier.size(24.dp)
                    )
                },
//                label = { Text(text = item.text) },
//                alwaysShowLabel = false


            )
        }
    }
}

private fun navigatorTo(navCtrl: NavHostController, route: String) {
    navCtrl.navigate(route) {
        popUpTo(navCtrl.graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
    }

}

@Preview
@Composable
fun PreviewMain() {
    MainScreen()
}