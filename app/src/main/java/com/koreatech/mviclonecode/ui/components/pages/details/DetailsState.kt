package com.koreatech.mviclonecode.ui.components.pages.details

import com.koreatech.data.entity.PokemonDetails
import com.koreatech.mviclonecode.ui.common.UiStatus

data class DetailsState(
    val status: UiStatus = UiStatus.Loading,
    val details: PokemonDetails? = null,
    val evolutions: List<PokemonDetails> = emptyList(),
)