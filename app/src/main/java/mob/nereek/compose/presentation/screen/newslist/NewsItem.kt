package mob.nereek.compose.presentation.screen.newslist

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mob.nereek.compose.R
import mob.nereek.compose.domain.model.Article
import mob.nereek.compose.presentation.navigation.Navigator
import mob.nereek.compose.presentation.navigation.arguments.StringParcelable
import mob.nereek.compose.presentation.ui.theme.BlueNews
import mob.nereek.compose.presentation.ui.theme.BlueNews_Dark
import mob.nereek.compose.presentation.ui.theme.Pink40
import java.util.Locale

@Composable
fun NewsCard(
    navigator:Navigator,
    article: Article,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(10.dp)
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8. dp,
            pressedElevation = 2. dp,
            focusedElevation = 4. dp,),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = if (article.urlToImage?.isNotEmpty() == true) {
                    rememberAsyncImagePainter(article.urlToImage)
                } else {
                    painterResource(id = R.drawable.ic_launcher_background)
                },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .heightIn(max = if (expanded) 300.dp else 150.dp)
                    .fillMaxWidth(),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(
                    IntrinsicSize.Min)
                ) {
                    Box(
                        modifier = Modifier
                            .width(5.dp)
                            .fillMaxHeight() // Remplit toute la hauteur disponible
                            .align(Alignment.CenterVertically)
                            .background(BlueNews_Dark)
                    )
                    Text(
                        text = article.title.toString(),
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = BlueNews,
                        maxLines = if (expanded) Int.MAX_VALUE else 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                            .padding(start = 5.dp)
                    )
                }

                Text(
                    text = article.content.toString(),
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    maxLines = if (expanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .padding(bottom = 5.dp, top = 5.dp)
                )

                if (expanded) {
                    Column(
                        modifier = Modifier.padding(bottom = 5.dp, top = 5.dp)
                    ) {

                        OpenInBrowserButton(navigator,article.url.toString(),modifier.align(Alignment.End))

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {


                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            ) {
                                Text(
                                    text = article.source?.name.toString(),
                                    color = BlueNews_Dark,
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.paddingFromBaseline(top = 16.dp)
                                )
                                Text(
                                    text = article.author.toString(),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Pink40,
                                    modifier =  Modifier.paddingFromBaseline(top = 16.dp)
                                )
                            }
                            Text(
                                text = article.publishedAt.toString(),
                                color = BlueNews_Dark,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier
                                    .align(Alignment.Bottom)
                                    .paddingFromBaseline(bottom = 24.dp)
                            )
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun OpenInBrowserButton(navigator:Navigator,urlnews: String,modifier : Modifier) {

    Button(
        onClick = {navigator.navigateToWebPage(newsUrlParcelable = urlnews) },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = BlueNews),
        shape = RoundedCornerShape(4.dp),
        content = {
            Text(
                text = stringResource(R.string.open_in_browser).uppercase(
                    Locale.ROOT
                ),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    )
}