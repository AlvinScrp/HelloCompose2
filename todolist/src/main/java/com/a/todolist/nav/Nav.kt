package com.a.todolist.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.a.todolist.ui.screen.detail.DetailScreen
import com.a.todolist.ui.screen.home.HomeScreen
import com.a.todolist.ui.viewmodels.MainViewModel

object Nav {

    private const val SCREEN_SPLASH = "splash"
    private const val SCREEN_HOME = "home"
    private const val SCREEN_DETAIL_Base = "detail?taskId="
    private const val SCREEN_DETAIL = "detail?taskId={taskId}"
    private const val ArgTaskId = "taskId"

    private var controller: NavController? = null

    fun goHome() {
        controller?.navigate(route = SCREEN_HOME) {
            popUpTo(SCREEN_HOME)
            launchSingleTop = true

        }
    }

    fun goDetail(taskId: Int) {
        Log.d("alvin","goDetail:$taskId")
        controller?.navigate(route = "${SCREEN_DETAIL_Base}${taskId}"){
            restoreState = true
        }
    }

    @Composable
    fun setupNav(vm: MainViewModel) {
        val navController = rememberNavController()
        this.controller = navController

        NavHost(navController = navController, startDestination = SCREEN_HOME) {
            composable(route = SCREEN_HOME) {
                HomeScreen(vm)
            }
            composable(
                route = SCREEN_DETAIL,
                arguments = listOf(
                    navArgument(ArgTaskId) { defaultValue = -1 })
            ) { backStackEntry ->
                var taskId = backStackEntry.arguments?.getInt(ArgTaskId)

                DetailScreen(vm, taskId)
            }
        }
    }
}

