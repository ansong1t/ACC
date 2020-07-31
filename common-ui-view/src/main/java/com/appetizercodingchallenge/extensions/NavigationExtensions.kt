package com.appetizercodingchallenge.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.navOptions
import com.appetizercodingchallenge.common.ui.R

fun NavController.navigateToNavDestination(itemId: Int, popUpToStart: Boolean = true): Boolean {
    val options = navOptions {
        launchSingleTop = true
        anim {
            enter = R.anim.acc_enter_anim
            exit = R.anim.acc_exit_anim
            popEnter = R.anim.acc_pop_enter_anim
            popExit = R.anim.acc_pop_exit_anim
        }
        if (popUpToStart) {
            popUpTo(graph.findStartDestination().id) {
                inclusive = false
            }
        }
    }

    return try {
        // TODO provide proper API instead of using Exceptions as Control-Flow.
        navigate(itemId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}

private fun NavGraph.findStartDestination(): NavDestination {
    var startDestination: NavDestination = this
    while (startDestination is NavGraph) {
        val parent = startDestination
        startDestination = parent.findNode(parent.startDestination)!!
    }
    return startDestination
}
