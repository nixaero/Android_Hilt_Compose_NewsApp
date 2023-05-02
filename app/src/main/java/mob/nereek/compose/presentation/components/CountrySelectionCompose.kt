package mob.nereek.compose.presentation.components

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import mob.nereek.compose.R

@Composable
fun CountrySelectionCompose(
    onCountrySelected: (String) -> Unit,
    modifier: Modifier
) {

    var selectedCountry by rememberSaveable { mutableStateOf("us") }

    Row(
        modifier =modifier.padding(15.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    selectedCountry = if (selectedCountry == "fr") "us" else "fr"
                    onCountrySelected(selectedCountry)
                })
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val frAlpha = animateFloatAsState(if (selectedCountry == "fr") 1f else 0.4f,
            animationSpec = TweenSpec(durationMillis = 800)
        )
        val usaAlpha = animateFloatAsState(if (selectedCountry == "us") 1f else 0.4f,
            animationSpec = TweenSpec(durationMillis = 800)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_united_states),
            contentDescription = stringResource(id = R.string.flag_usa),
            modifier = Modifier
                .padding(5.dp)
                .size(38.dp)
                .graphicsLayer(
                    alpha = usaAlpha.value,
                ),
        )

        Image(
            painter = painterResource(id = R.drawable.ic_france),
            contentDescription = stringResource(id = R.string.flag_fr),
            modifier = Modifier
                .padding(5.dp)
                .size(38.dp)
                .graphicsLayer(
                    alpha = frAlpha.value,
                ),
        )
    }

}