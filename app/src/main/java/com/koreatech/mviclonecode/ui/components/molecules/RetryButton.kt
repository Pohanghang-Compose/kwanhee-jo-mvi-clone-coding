package com.koreatech.mviclonecode.ui.components.molecules

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.koreatech.mviclonecode.R

@Composable
fun RetryButton(modifier: Modifier = Modifier, onClick: (() -> Unit)? = null) {
    Button(onClick = { onClick?.invoke() }, modifier = modifier) {
        Text(text = stringResource(id = R.string.retry))
    }
}

@Preview
@Composable
fun RetryButtonPreview() {
    RetryButton(Modifier.wrapContentSize())
}