package com.koreatech.mviclonecode.ui.components.pages.library

sealed class LibrarySideEffect {
    data class ShowDetail(val id: Int): LibrarySideEffect()
}