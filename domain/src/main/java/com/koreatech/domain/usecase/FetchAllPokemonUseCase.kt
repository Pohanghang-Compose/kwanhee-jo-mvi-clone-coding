package com.koreatech.domain.usecase

import com.koreatech.data.repository.ConfigRepository
import com.koreatech.data.repository.PokemonRepository

class FetchAllPokemonUseCase(
    private val pokemonRepository: PokemonRepository,
    private val configRepository: ConfigRepository,
) {
    suspend operator fun invoke(): Boolean {
        return if (!configRepository.createdDatabase) {
            pokemonRepository.clear()
            pokemonRepository.fetch().apply { configRepository.createdDatabase = this }
        } else true
    }
}