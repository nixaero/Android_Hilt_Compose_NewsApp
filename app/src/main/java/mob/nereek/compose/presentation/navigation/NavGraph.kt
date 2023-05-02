package mob.nereek.compose.presentation.navigation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mob.nereek.compose.common.Constants
import mob.nereek.compose.presentation.navigation.arguments.StringParcelable
import mob.nereek.compose.presentation.screen.SplashScreen
import mob.nereek.compose.presentation.screen.newslist.NewsListScreen
import mob.nereek.compose.presentation.screen.webpage.WebPageScreen

@Composable
fun NavGraph() {
    // Create a NavController to handle navigation within the app
    val navController = rememberNavController()
    // Create a ComposeNavigator that implements the Navigator interface
    val navigator: Navigator = NavigatorImpl(navController)

    // Define the screens that can be navigated to
    NavHost(
        navController = navController,
        startDestination = NavScreen.SplashScreen.route
    ) {
        // Define the SplashScreen screen
        composable(NavScreen.SplashScreen.route) {
            SplashScreen(navigator)
        }
        // Define the NewsListScreen screen
        composable(NavScreen.NewsListScreen.route) {
            NewsListScreen(navigator)
        }

        // Déclarer la destination avec des arguments dans NavGraph

        composable(route = NavScreen.WebPageScreen.route) { backStackEntry ->
            //val newsUrl: String = backStackEntry.arguments?.getParcelable<StringParcelable>(WEB_URL_KEY)?.value ?: ""
            val newsUrl = navController.previousBackStackEntry?.savedStateHandle?.get<String>(Constants.WEB_URL_KEY)

            Log.d(TAG, "NavGraph: ${newsUrl}")

            if (newsUrl != null) {
                WebPageScreen(navigator = navigator, navController = navController, urlToRender = newsUrl)
            }
        }
    }
}
