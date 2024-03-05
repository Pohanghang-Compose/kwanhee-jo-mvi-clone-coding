package com.koreatech.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.koreatech.data.entity.PokemonDetails
import com.koreatech.data.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Insert
    suspend fun insert(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemons")
    suspend fun getAll(): List<PokemonDetails>

    @Query("SELECT * FROM pokemons WHERE id=:id")
    suspend fun getById(id: Int): PokemonDetails?

    @Query("SELECT * FROM pokemons WHERE num IN (:numbers)")
    suspend fun getByNumbers(numbers: List<String>): List<PokemonDetails>
}