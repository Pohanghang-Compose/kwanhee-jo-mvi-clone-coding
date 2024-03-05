package com.koreatech.domain.usecase

import com.koreatech.data.entity.PokemonDetails
import com.koreatech.data.repository.PokemonRepository

class LoadPokemonDetailsUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): PokemonDetails? {
        return pokemonRepository.getById(id)
    }
}