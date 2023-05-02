package mob.nereek.compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.AndroidEntryPoint
import mob.nereek.compose.presentation.navigation.NavGraph
import mob.nereek.compose.presentation.ui.theme.BlueNews

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(
                    primary = Color.White,
                    secondary = BlueNews,
                    background = BlueNews,
                    onBackground = BlueNews
                ),
            ) {
                NavGraph()
            }
        }
    }
}