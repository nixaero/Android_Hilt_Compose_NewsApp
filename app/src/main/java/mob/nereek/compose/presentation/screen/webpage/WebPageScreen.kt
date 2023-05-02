package mob.nereek.compose.presentation.screen.webpage

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import mob.nereek.compose.common.Constants.WEB_URL_KEY
import mob.nereek.compose.presentation.navigation.Navigator
import mob.nereek.compose.presentation.navigation.arguments.StringParcelable


@SuppressLint("SetJavaScriptEnabled") // Suppresses lint warning for enabling JavaScript in WebView
@Composable
fun WebPageScreen(navigator: Navigator, navController: NavHostController, urlToRender: String = "") {
    var isLoading by rememberSaveable { mutableStateOf(true) } // State to track if the WebView is still loading

    // Create an AndroidView composable to display a WebView
    AndroidView(factory = {
        WebView(it).apply { // Set up the WebView
            // Set the layout parameters to match the parent's size
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            // Set a WebViewClient to handle page loading events
            webViewClient = object : WebViewClient() {

                // Called when the page starts loading
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    isLoading = true // Set isLoading to true to show the loading spinner
                }

                // Called when the page finishes loading
                override fun onPageFinished(view: WebView?, url: String?) {
                    isLoading = false // Set isLoading to false to hide the loading spinner
                }
            }
            // Load the specified URL
            if (urlToRender != null) {
                loadUrl(urlToRender)
            }
        }
    }, update = {
        if (urlToRender != null) {
            it.loadUrl(urlToRender)
        } // Reload the URL if it has changed
    })

    // Show a loading spinner while the page is still loading
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
