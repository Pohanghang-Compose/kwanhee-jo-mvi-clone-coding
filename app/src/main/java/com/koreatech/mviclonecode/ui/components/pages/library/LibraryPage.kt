package com.koreatech.mviclonecode.ui.components.pages.library

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.koreatech.data.entity.PokemonDetails
import com.koreatech.mviclonecode.ui.common.UiStatus
import com.koreatech.mviclonecode.ui.components.molecules.ErrorMessage
import com.koreatech.mviclonecode.ui.components.molecules.LoadingIndicator
import com.koreatech.mviclonecode.ui.components.molecules.SearchBar
import com.koreatech.mviclonecode.ui.components.molecules.TopBar
import com.koreatech.mviclonecode.ui.components.organisms.PokemonTwoCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryPage(
    state: LibraryState,
    onShowDetail: (id: Int) -> Unit,
    onSearchPokemon: (keyword: String) -> Unit,
) {
    Log.e("aaa", "LibraryPage state : ${state.status}")

    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        SearchBar(
                            searchText = state.searchText,
                            onChangedSearchText = { onSearchPokemon(it) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }

                    if (state.status == UiStatus.Success) {
                        setupTwoGrid(state.detailList) { one, two ->
                            PokemonTwoCard(
                                one = one,
                                onClickedOne = { one?.let { onShowDetail(it.pokemon.id) } },
                                two = two,
                                onClickedTwo = { two?.let { onShowDetail(it.pokemon.id) } },
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                                    .padding(bottom = 8.dp)
                            )
                        }
                    }
                }

                when (val status = state.status) {
                    UiStatus.Loading -> {
                        LoadingIndicator(modifier = Modifier.fillMaxSize())
                    }

                    is UiStatus.Failed -> {
                        ErrorMessage(message = status.message, modifier = Modifier.fillMaxSize())
                    }

                    else -> Unit
                }
            }
        }
    )
}

private fun LazyListScope.setupTwoGrid(
    entities: List<PokemonDetails>,
    row: @Composable (one: PokemonDetails?, two: PokemonDetails?) -> Unit,
) {
    val rowData = if (entities.count() <= 2) {
        listOf(entities)
    } else {
        entities.windowed(size = 2, step = 2)
    }

    rowData.forEach { column ->
        item { row(column.getOrNull(0), column.getOrNull(1)) }
    }
}