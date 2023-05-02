package mob.nereek.compose.presentation.navigation

import mob.nereek.compose.presentation.navigation.arguments.StringParcelable

/**
 * Represents the possible screens in the app and their associated navigation routes.
 */
sealed class NavScreen(val route: String) {

    object SplashScreen : NavScreen("splash")
    object NewsListScreen : NavScreen("news_list")
    object WebPageScreen : NavScreen("webPage")

}