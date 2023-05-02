package mob.nereek.compose.presentation.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import mob.nereek.compose.R
import mob.nereek.compose.presentation.navigation.Navigator
import mob.nereek.compose.presentation.ui.theme.BlueNews


@Composable
fun SplashScreen(navigator: Navigator?) {
    val scale = remember {
        Animatable(0f)
    }

    val rotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 180f,
            animationSpec = repeatable(
                iterations = 1,
                animation = tween(2500),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 0.1f,
            animationSpec = tween(
                durationMillis = 2500,
                easing = {
                    OvershootInterpolator(1f).getInterpolation(it)
                }
            )

        )
        delay(2500L)
        navigator?.navigateToNewsList()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueNews),
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "News App Logo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize(.7f)
                    .graphicsLayer {
                        rotationY = rotation.value
                    }
            )
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Text(text = stringResource(id = R.string.nereek_2023), color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen(null)
}