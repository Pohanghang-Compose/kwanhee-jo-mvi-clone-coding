package com.koreatech.domain

import com.koreatech.domain.usecase.FetchAllPokemonUseCase
import com.koreatech.domain.usecase.LoadPokemonDetailsUseCase
import com.koreatech.domain.usecase.LoadPokemonEvolutionUseCase
import com.koreatech.domain.usecase.SearchPokemonFromNameUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        FetchAllPokemonUseCase(pokemonRepository = get(), configRepository = get())
    }
    factory {
        SearchPokemonFromNameUseCase(pokemonRepository = get())
    }
    factory {
        LoadPokemonDetailsUseCase(pokemonRepository = get())
    }
    factory {
        LoadPokemonEvolutionUseCase(pokemonRepository = get())
    }
}