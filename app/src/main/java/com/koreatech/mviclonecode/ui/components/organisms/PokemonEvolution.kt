package com.koreatech.mviclonecode.ui.components.organisms

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.koreatech.data.entity.PokemonDetails
import com.koreatech.mviclonecode.R
import jp.kaleidot725.orbit.ui.SAMPLE_POKEMON_DETAILS

@Composable
fun PokemonEvolution(
    details: PokemonDetails,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Row(modifier = Modifier.height(50.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(details.image.localUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(
                    if (isSystemInDarkTheme()) {
                        R.drawable.ic_question_white
                    } else R.drawable.ic_question_black
                ),
                error = painterResource(id = R.drawable.ic_error),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 16.dp)
            )

            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = details.pokemon.name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .align(
                            Alignment.CenterStart
                        )
                        .wrapContentHeight()
                        .padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PokemonEvolutionPreview() {
    PokemonEvolution(
        details =
        SAMPLE_POKEMON_DETAILS, modifier = Modifier.wrapContentHeight()
    )
}

