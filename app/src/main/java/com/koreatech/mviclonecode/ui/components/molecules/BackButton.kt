package com.koreatech.mviclonecode.ui.components.molecules

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    OutlinedButton(
        onClick = { onClick?.invoke() },
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.White),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Black,
            containerColor = Color.White
        )
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)

    }
}

@Preview
@Composable
fun BackButtonPreview() {
    BackButton(
        modifier = Modifier.size(50.dp)
    )
}