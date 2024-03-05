package com.koreatech.mviclonecode.ui.components.pages.library

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.domain.usecase.SearchPokemonFromNameUseCase
import com.koreatech.mviclonecode.ui.common.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class LibraryViewModel(
    private val searchPokemonUseCase: SearchPokemonFromNameUseCase,
) : ContainerHost<LibraryState, LibrarySideEffect>, ViewModel() {
    private var searchJob: Job? = null

    override val container: Container<LibraryState, LibrarySideEffect>
        get() = container(LibraryState())

    init {
        intent {
            searchPokemon(state.searchText)
        }
    }

    fun searchPokemon(searchText: String) {
        intent {
            searchJob?.cancel()
            searchJob = viewModelScope.launch(Dispatchers.IO) {
                reduce {
                    state.copy(
                        status = UiStatus.Loading,
                        searchText = searchText,
                        detailList = emptyList()
                    )
                }

                val details = searchPokemonUseCase(state.searchText)
                delay(1000)
                Log.e("aaa", "details : ${details}")

                if (details.isNotEmpty()) {
                    Log.e("aaa", "details : ?!!")
                    Log.e("aaa", "details : ?!! state ${state.status}")
                    Log.e("aaa", "details : ?!! state ${state.hashCode()}")
                    reduce {
                        state.copy(
                            status = UiStatus.Success,
                            detailList = details
                        )
                    }
                    Log.e("aaa", "details : ?!! after state ${state.status}")
                    Log.e("aaa", "details : ?!! after state ${state.hashCode()}")
                } else {
                    Log.e("aaa", "details : ??")
                    reduce {
                        state.copy(
                            status = UiStatus.Failed("Not Found"),
                            detailList = details
                        )
                    }
                }
            }
        }
    }

    fun showDetails(id: Int) {
        intent {
            postSideEffect(LibrarySideEffect.ShowDetail(id))
        }
    }
}