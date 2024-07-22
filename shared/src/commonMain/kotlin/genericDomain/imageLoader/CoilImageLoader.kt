package genericDomain.imageLoader

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
@OptIn(ExperimentalResourceApi::class)
fun CoilImage(
    modifier: Modifier = Modifier,
    url: String,
    placeHolder: String,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = null,
        contentScale = contentScale,
        placeholder = painterResource(placeHolder),
        error = painterResource(placeHolder)
    )
}