package com.uncp.myproyectosesion5.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uncp.myproyectosesion5.R
import com.uncp.myproyectosesion5.data.models.ArticleItem
import com.uncp.myproyectosesion5.data.models.ResponseModel
import com.uncp.myproyectosesion5.data.models.SourceModel
import com.uncp.myproyectosesion5.presentation.components.CustomAppBar
import com.uncp.myproyectosesion5.ui.theme.PublishedText
import com.uncp.myproyectosesion5.ui.theme.TitleText
import com.uncp.myproyectosesion5.ui.viewmodel.MainActivityViewModel

@Composable
fun Screen1(drawerState: DrawerState, viewModel: MainActivityViewModel) {
    viewModel.getListDataApi()
    val dataResponse: ResponseModel? by viewModel.dataResponse.observeAsState(null)
    Scaffold(
        topBar = { CustomAppBar(drawerState = drawerState, title = "Articles") }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (dataResponse != null && !dataResponse?.articles.isNullOrEmpty()) {
                items(count = dataResponse?.articles?.size ?: 0, itemContent = {
                    dataResponse?.articles?.getOrNull(it).let { article ->
                        NewsItem(article)
                    }
                })
            }
        }
    }
}

@Composable
fun NewsItem(article: ArticleItem?) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            Row {
                AsyncImage(
                    model = article?.urlToImage,
                    placeholder = painterResource(id = R.drawable.defaultimage),
                    contentDescription = "News image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(screenWidth / 3)
                        .clip(
                            RoundedCornerShape(
                                topStart = 5.dp,
                                bottomEnd = 5.dp
                            )
                        ),
                    error = painterResource(id = R.drawable.defaultimage)
                )
                Text(
                    text = article?.title.orEmpty(),
                    style = TitleText,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Text(text = article?.description.orEmpty())
            Text(text = article?.content.orEmpty(), maxLines = 3, overflow = TextOverflow.Ellipsis)
            Text(text = "Publicado en: ${article?.publishedAt}", style = PublishedText)
        }
    }
}

@Preview
@Composable
fun NewsItemPreview() {
    NewsItem(
        article = ArticleItem(
            source = SourceModel(null, ""),
            author = "Antony",
            title = "Titulo",
            description = "Descripcion larga de mucho texto",
            url = "https://assets.aboutamazon.com/dims4/default/22531a7/2147483647/strip/true/crop/1600x800+0+50/resize/1200x600!/quality/90/?url=https%3A%2F%2Famazon-blogs-brightspot.s3.amazonaws.com%2F34%2F10%2Fef5be8204f978aeac7a5a8584d84%2F1-header-standardinlinehero-1600x900.gif",
            urlToImage = "https://assets.aboutamazon.com/dims4/default/22531a7/2147483647/strip/true/crop/1600x800+0+50/resize/1200x600!/quality/90/?url=https%3A%2F%2Famazon-blogs-brightspot.s3.amazonaws.com%2F34%2F10%2Fef5be8204f978aeac7a5a8584d84%2F1-header-standardinlinehero-1600x900.gif",
            publishedAt = "10-12-2024",
            content = ""
        )
    )
}