package com.koreatech.mviclonecode.ui.components.pages.details

import androidx.lifecycle.ViewModel
import com.koreatech.domain.usecase.LoadPokemonDetailsUseCase
import com.koreatech.domain.usecase.LoadPokemonEvolutionUseCase
import com.koreatech.mviclonecode.ui.common.UiStatus
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DetailsViewModel(
    private val id: Int,
    private val loadPokemonDetailsUseCase: LoadPokemonDetailsUseCase,
    private val loadPokemonEvolutionUseCase: LoadPokemonEvolutionUseCase,
) : ContainerHost<DetailsState, DetailsSideEffect>, ViewModel() {
    override val container: Container<DetailsState, DetailsSideEffect>
        get() = container(DetailsState())

    init {
        intent {
            val details = loadPokemonDetailsUseCase(id)
            val evolutions = loadPokemonEvolutionUseCase(id)
            if (details != null) {
                reduce {
                    state.copy(
                        status = UiStatus.Success,
                        details = details,
                        evolutions = evolutions
                    )
                }
            } else {
                reduce {
                    state.copy(
                        status = UiStatus.Failed("Loading Error..."),
                        details = null,
                        evolutions = emptyList()
                    )
                }
            }
        }
    }
}