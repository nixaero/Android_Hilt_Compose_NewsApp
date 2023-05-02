package mob.nereek.compose.presentation.navigation

import mob.nereek.compose.presentation.navigation.arguments.StringParcelable


/**
 * Navigator interface used for navigating between different screens.
 */
interface Navigator {

    fun navigateToSplash()

    fun navigateToNewsList()

    fun navigateToWebPage(newsUrlParcelable: String)
}