package mob.nereek.compose.presentation.screen.newslist

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import mob.nereek.compose.R
import mob.nereek.compose.presentation.navigation.Navigator
import mob.nereek.compose.presentation.ui.theme.BlueNews
import androidx.compose.ui.layout.layout
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import mob.nereek.compose.common.Resource
import mob.nereek.compose.domain.model.Article
import mob.nereek.compose.presentation.components.CountrySelectionCompose


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsListScreen(navigator: Navigator, viewModel: NewsListViewModel = hiltViewModel()) {
    val uiState by viewModel.news.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    Scaffold(
        topBar = {},
        containerColor = BlueNews
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BlueNews),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val (image, text, row, list) = createRefs()

                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(text.start)
                            }
                            .fillMaxWidth(.23f)
                            .aspectRatio(.7f)
                            .height(IntrinsicSize.Max)
                    )

                    Text(
                        text = stringResource(id = R.string.news),
                        modifier = Modifier.constrainAs(text) {
                            start.linkTo(image.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(image.bottom)
                        },
                        fontSize = 34.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )

                    CountrySelectionCompose(
                        onCountrySelected = { country -> viewModel.setCountry(country)},
                        modifier = Modifier.constrainAs(row) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(image.bottom)
                        })


                    ArticleList(
                        uiState = uiState,
                        navigator,
                        modifier = Modifier.constrainAs(list) {
                            top.linkTo(image.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                        }.padding(bottom = 64.dp).zIndex(10f))

                }
            }
        }
    }
}

@Composable
fun ArticleList(
    uiState: Resource<List<Article>>,
    navigator: Navigator,
    modifier: Modifier
) {
    when (uiState) {
        is Resource.Loading -> {
            // Show a loading indicator
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxHeight()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
            }
        }

        is Resource.Success -> {
            // Extract the list of articles from the UI state
            val articles = uiState.data

            // Show the list of articles using a LazyColumn
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            placeable.place(0, 120) // Position the column below the image
                        }
                    }
            ) {
                items(articles) { article ->
                    NewsCard(navigator,article = article)
                }
            }
        }

        is Resource.Error -> {
            // Show an error message
            Text(text = uiState.errorMessage, modifier = Modifier.padding(16.dp))
        }
    }

}

fun onArticleClicked(article: Article) {
    // Handle the click event here
}

@Preview
@Composable
fun PreviewNewsListScreen() {
    //NewsListScreen(null)
}