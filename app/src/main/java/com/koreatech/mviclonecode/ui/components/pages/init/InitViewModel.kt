package com.koreatech.mviclonecode.ui.components.pages.init

import android.util.Log
import androidx.lifecycle.ViewModel
import com.koreatech.domain.usecase.FetchAllPokemonUseCase
import com.koreatech.mviclonecode.ui.common.UiStatus
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class InitViewModel(
    private val fetchAllPokemonUseCase: FetchAllPokemonUseCase,
) : ContainerHost<InitState, InitSideEffect>, ViewModel() {
    override val container: Container<InitState, InitSideEffect>
        = container(InitState())

    init {
        fetchData()
    }

    fun retry() {
        if (container.stateFlow.value.status != UiStatus.Loading) {
            fetchData()
        }
    }

    private fun fetchData() {
        intent {
            reduce {
                state.copy(status = UiStatus.Loading) }
            if (fetchAllPokemonUseCase()) {
                Log.e("aaa", ": ?!! state ${state.status}")
                Log.e("aaa", ": ?!! state ${state.hashCode()}")
                reduce { state.copy(status = UiStatus.Success) }
                Log.e("aaa", ": ?!! state ${state.status}")
                Log.e("aaa", ": ?!! state ${state.hashCode()}")
                postSideEffect(InitSideEffect.Completed)
            } else {
                reduce { state.copy(status = UiStatus.Failed()) }
            }
        }
    }
}
