package com.a.wanandroid.nav

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.a.wanandroid.screen.ArticleDetailScreen
import com.a.wanandroid.screen.MainScreen
import com.a.wanandroid.screen.SplashScreen

@Composable
fun NavigationApp() {
    val navCtrl = rememberNavController()
    NavHost(navController = navCtrl, startDestination = RouteData.splash) {
        composable(RouteData.splash) {
            SplashScreen()
        }
        composable(RouteData.main) {
            MainScreen()
        }
        composable(RouteData.article_detail) {
            ArticleDetailScreen()
        }
    }

    Handler(Looper.getMainLooper()).postDelayed({
        navCtrl.navigate(RouteData.main) {
            popUpTo(RouteData.splash) { inclusive = true }
        }
    }, 500)
}


