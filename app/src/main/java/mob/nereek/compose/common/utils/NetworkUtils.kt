package mob.nereek.compose.common.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject


class NetworkUtils @Inject constructor(private val connectivityManager: ConnectivityManager) {
    /**
     * Check whether the device is currently connected to the internet.
     *
     * @return true if the device is connected to the internet, false otherwise.
     */
    fun isConnected(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // If the device is running Android M or later, use the new network APIs to check for internet connectivity.
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            // If the device is running an earlier version of Android, use the legacy network APIs to check for internet connectivity.
            val activeNetwork = connectivityManager.activeNetworkInfo ?: return false
            return activeNetwork.isConnectedOrConnecting
        }
    }
}
