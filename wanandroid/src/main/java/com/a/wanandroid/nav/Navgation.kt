package com.a.wanandroid.nav

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.a.wanandroid.net.bean.Article
import com.a.wanandroid.screen.ArticleDetailScreen
import com.a.wanandroid.screen.MainScreen
import com.a.wanandroid.screen.SplashScreen
import java.net.URLDecoder
import java.net.URLEncoder

object Navigation {


    fun goDetail(
        navController: NavHostController,
        title: String? = null,
        link: String? = null,
    ) {
        val route = String.format(
            RouteData.article_detail_format,
            URLEncoder.encode(title),
            URLEncoder.encode(link)
        )
        navController.navigate(route) {
            restoreState = true
        }
    }
}

@Composable
fun NavigationApp() {
    val navCtrl = rememberNavController()
    NavHost(navController = navCtrl, startDestination = RouteData.splash) {
        composable(RouteData.splash) {
            SplashScreen()
        }
        composable(RouteData.main) {
            MainScreen { article ->
                Log.d("alvin", "goDetail:${article.title}")
                Navigation.goDetail(navCtrl, article.title, article.link)

            }
        }
        composable(
            RouteData.article_detail,
            arguments = listOf(
                navArgument("title") { defaultValue = "" },
                navArgument("link") { defaultValue = "" })
        ) {
            var title = URLDecoder.decode(it.arguments?.getString("title").orEmpty())
            var link = URLDecoder.decode(it.arguments?.getString("link").orEmpty())
            ArticleDetailScreen(title , link){
                navCtrl.popBackStack()
            }
        }
    }

    Handler(Looper.getMainLooper()).postDelayed({
        navCtrl.navigate(RouteData.main) {
            popUpTo(RouteData.splash) { inclusive = true }
        }
    }, 500)
}







