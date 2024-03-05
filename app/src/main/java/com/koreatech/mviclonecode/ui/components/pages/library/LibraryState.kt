package com.koreatech.mviclonecode.ui.components.pages.library

import com.koreatech.data.entity.PokemonDetails
import com.koreatech.mviclonecode.ui.common.UiStatus

data class LibraryState(
    val status: UiStatus? = UiStatus.Loading,
    val searchText: String = "",
    val detailList: List<PokemonDetails> = emptyList(),
)