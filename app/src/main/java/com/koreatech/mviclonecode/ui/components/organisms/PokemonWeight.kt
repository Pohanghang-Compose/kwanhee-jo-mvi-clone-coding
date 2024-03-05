package com.koreatech.mviclonecode.ui.components.organisms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.koreatech.mviclonecode.ui.components.molecules.SmallCard

@Composable
fun PokemonWeight(
    weight: String,
    modifier: Modifier = Modifier
) {
    SmallCard(text = "Weight : $weight", modifier = modifier)
}