package com.koreatech.mviclonecode.ui.components.molecules

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingMessage(message: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = message,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun LoadingMessagePreview() {
    LoadingMessage(message = "Download Pokemon Data...")
}