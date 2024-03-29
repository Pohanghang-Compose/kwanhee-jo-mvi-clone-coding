package com.koreatech.mviclonecode.ui.components.pages.init

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.koreatech.mviclonecode.ui.common.UiStatus
import com.koreatech.mviclonecode.ui.components.organisms.DownloadRetryMessage
import com.koreatech.mviclonecode.ui.components.organisms.DownloadingMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitPage(
    state: InitState,
    onRetry: () -> Unit,
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (state.status) {
                UiStatus.Loading -> {
                    DownloadingMessage(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                    )
                }

                is UiStatus.Failed -> {
                    DownloadRetryMessage(
                        onRetry = { onRetry() },
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                    )
                }

                UiStatus.Success -> Unit
                null -> Unit
            }
        }
    }
}