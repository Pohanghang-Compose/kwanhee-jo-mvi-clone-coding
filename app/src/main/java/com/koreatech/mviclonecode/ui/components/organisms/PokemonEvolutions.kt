package com.koreatech.mviclonecode.ui.components.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.koreatech.data.entity.PokemonDetails
import com.koreatech.mviclonecode.R

@Composable
fun PokemonEvolutions(
    details: List<PokemonDetails>,
    modifier: Modifier =Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.evolution_title),
            style = MaterialTheme.typography.headlineMedium,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            details.forEach {
                PokemonEvolution(details = it)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}