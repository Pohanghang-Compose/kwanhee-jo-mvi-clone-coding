package com.koreatech.mviclonecode.ui.components.organisms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.koreatech.mviclonecode.ui.components.molecules.SmallCard

@Composable
fun PokemonHeight(
    height: String,
    modifier: Modifier = Modifier
) {
    SmallCard(text = "Height : $height", modifier = modifier)
}