package com.koreatech.mviclonecode.ui.components.pages.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.data.entity.PokemonDetails
import com.koreatech.mviclonecode.ui.common.UiStatus
import com.koreatech.mviclonecode.ui.components.molecules.BackButton
import com.koreatech.mviclonecode.ui.components.molecules.ErrorMessage
import com.koreatech.mviclonecode.ui.components.molecules.LoadingIndicator
import com.koreatech.mviclonecode.ui.components.organisms.PokemonBreeding
import com.koreatech.mviclonecode.ui.components.organisms.PokemonEvolutions
import com.koreatech.mviclonecode.ui.components.organisms.PokemonPortrait
import com.koreatech.mviclonecode.ui.components.organisms.PokemonTypes
import com.koreatech.mviclonecode.ui.components.organisms.PokemonWeaknesses

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPage(
    state: DetailsState,
    onBack: () -> Unit,
) {
    Scaffold {
        when (val status = state.status) {
            UiStatus.Loading -> {
                LoadingIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                )
            }

            is UiStatus.Failed -> {
                ErrorMessage(
                    message = status.message,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                )
            }

            UiStatus.Success -> {
                val details = state.details ?: return@Scaffold
                val evolutions = state.evolutions

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    PokemonDataList(
                        details = details,
                        evolutions = evolutions,
                        modifier = Modifier.fillMaxSize()
                    )

                    BackButton(
                        onClick = { onBack.invoke() },
                        modifier = Modifier
                            .size(48.dp)
                            .padding(top = 12.dp, start = 12.dp)
                            .align(Alignment.TopStart)
                    )
                }
            }
        }
    }
}

@Composable
private fun PokemonDataList(
    details: PokemonDetails,
    evolutions: List<PokemonDetails>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        item {
            PokemonPortrait(
                pokemonDetails = details,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
        item {
            PokemonTypes(
                details = details,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            )
        }
        item {
            PokemonWeaknesses(
                details = details,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            )
        }

        item {
            PokemonBreeding(
                pokemonEntity = details.pokemon,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            )
        }

        item {
            PokemonEvolutions(
                details = evolutions,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            )
        }

    }
}