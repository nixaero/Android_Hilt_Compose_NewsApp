package mob.nereek.compose.presentation.navigation

import androidx.navigation.NavHostController
import mob.nereek.compose.common.Constants.WEB_URL_KEY
import mob.nereek.compose.presentation.navigation.arguments.StringParcelable

/**
 * ComposeNavigator is a class that implements the Navigator interface
 * and is responsible for navigating to different screens in the app using
 * a NavHostController.
 *
 * @param navController is a NavHostController instance that is used for navigating
 * to different screens.
 */
class NavigatorImpl(private val navController: NavHostController) : Navigator {


    override fun navigateToSplash() {
        navController.navigate(NavScreen.SplashScreen.route)
    }

    override fun navigateToNewsList() {
        navController.navigate(NavScreen.NewsListScreen.route)
    }

    override fun navigateToWebPage(newsUrl: String) {
        navController.currentBackStackEntry?.savedStateHandle?.set(WEB_URL_KEY,newsUrl)
        navController.navigate(NavScreen.WebPageScreen.route)
    }
}