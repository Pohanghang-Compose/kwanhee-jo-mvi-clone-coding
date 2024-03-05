package com.koreatech.mviclonecode.ui.components.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.data.entity.PokemonDetails
import com.koreatech.mviclonecode.R
import jp.kaleidot725.orbit.ui.SAMPLE_POKEMON_DETAILS

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonWeaknesses(
    details: PokemonDetails,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(
            text = stringResource(id = R.string.weakness_title),
            style = MaterialTheme.typography.headlineMedium,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow {
            details.weaknesses.forEach { weakness ->
                PokemonWeakness(weakness = weakness, modifier = Modifier.padding(end = 8.dp))

            }
        }
    }
}

@Preview
@Composable
private fun PokemonWeaknessesPreview() {
    PokemonWeaknesses(
        details =
        SAMPLE_POKEMON_DETAILS,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
    )
}