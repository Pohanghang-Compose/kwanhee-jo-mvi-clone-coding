package com.koreatech.domain.usecase

import android.util.Log
import com.koreatech.data.entity.PokemonDetails
import com.koreatech.data.repository.PokemonRepository

class SearchPokemonFromNameUseCase(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(searchText: String): List<PokemonDetails> {
        return if (searchText.isEmpty()) {
            pokemonRepository.getAll()
        } else {
            pokemonRepository.getAll().filter { it.pokemon.name.contains(searchText) }
        }
    }
}
