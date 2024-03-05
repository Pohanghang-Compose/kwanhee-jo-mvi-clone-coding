package com.koreatech.domain.usecase

import com.koreatech.data.entity.PokemonDetails
import com.koreatech.data.repository.PokemonRepository

class LoadPokemonEvolutionUseCase(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(id: Int): List<PokemonDetails> {
        val details = pokemonRepository.getById(id) ?: return emptyList()
        val prevNumbers = details.prevEvolutions.map { it.num }
        val nextNumbers = details.nextEvolutions.map { it.num }
        val allNumbers = prevNumbers + details.pokemon.num + nextNumbers
        return pokemonRepository.getByNumbers(allNumbers)
    }
}