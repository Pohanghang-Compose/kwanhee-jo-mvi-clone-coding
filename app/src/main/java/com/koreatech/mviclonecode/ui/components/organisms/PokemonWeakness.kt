package com.koreatech.mviclonecode.ui.components.organisms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.koreatech.data.entity.WeaknessEntity
import com.koreatech.data.entity.WeaknessEntity.Companion.toPokemonType
import com.koreatech.data.enum.PokemonType
import com.koreatech.mviclonecode.ui.components.molecules.SmallCard
import com.koreatech.mviclonecode.ui.theme.Bug
import com.koreatech.mviclonecode.ui.theme.Dragon
import com.koreatech.mviclonecode.ui.theme.Electric
import com.koreatech.mviclonecode.ui.theme.Fighting
import com.koreatech.mviclonecode.ui.theme.Fire
import com.koreatech.mviclonecode.ui.theme.Flying
import com.koreatech.mviclonecode.ui.theme.Ghost
import com.koreatech.mviclonecode.ui.theme.Grass
import com.koreatech.mviclonecode.ui.theme.Ground
import com.koreatech.mviclonecode.ui.theme.Ice
import com.koreatech.mviclonecode.ui.theme.Normal
import com.koreatech.mviclonecode.ui.theme.Poison
import com.koreatech.mviclonecode.ui.theme.Psychic
import com.koreatech.mviclonecode.ui.theme.Rock
import com.koreatech.mviclonecode.ui.theme.Unknown
import com.koreatech.mviclonecode.ui.theme.Water

@Composable
fun PokemonWeakness(
    weakness: WeaknessEntity,
    modifier: Modifier = Modifier
) {
    SmallCard(
        text = weakness.value,
        backgroundColor = weakness.toPokemonType().toColor(),
        modifier = modifier
    )
}

private fun PokemonType.toColor(): androidx.compose.ui.graphics.Color {
    return when (this) {
        PokemonType.GRASS -> Grass
        PokemonType.POISON -> Poison
        PokemonType.FIRE -> Fire
        PokemonType.FLYING -> Flying
        PokemonType.WATER -> Water
        PokemonType.BUG -> Bug
        PokemonType.NORMAL -> Normal
        PokemonType.ELECTRIC -> Electric
        PokemonType.GROUND -> Ground
        PokemonType.FIGHTING -> Fighting
        PokemonType.PSYCHIC -> Psychic
        PokemonType.ROCK -> Rock
        PokemonType.ICE -> Ice
        PokemonType.GHOST -> Ghost
        PokemonType.DRAGON -> Dragon
        PokemonType.UNKNOWN -> Unknown
    }
}