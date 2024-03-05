package com.koreatech.data.utils.mapper

import com.koreatech.data.dto.PokemonDto
import com.koreatech.data.entity.ImageEntity
import com.koreatech.data.entity.MultiplierEntity
import com.koreatech.data.entity.NextEvolutionEntity
import com.koreatech.data.entity.PokemonEntity
import com.koreatech.data.entity.PrevEvolutionEntity
import com.koreatech.data.entity.TypeEntity
import com.koreatech.data.entity.WeaknessEntity

fun PokemonDto.toPokemonEntity(): PokemonEntity = PokemonEntity(
    id = id,
    avgSpawns = this.avgSpawns,
    candy = this.candy ?: "",
    candyCount = this.candyCount,
    egg = this.egg ?: "",
    height = this.height ?: "",
    name = this.name ?: "",
    num = this.num ?: "",
    spawnChance = this.spawnChance,
    spawnTime = this.spawnTime ?: "",
    weight = this.weight ?: "",
)

fun PokemonDto.toMultiplierEntities(): List<MultiplierEntity> {
    return this.multipliers?.map { multiplier ->
        MultiplierEntity(
            id = 0,
            pokemonId = this.id,
            value = multiplier
        )
    } ?: emptyList()
}

fun PokemonDto.toNextEvolutionEntities(): List<NextEvolutionEntity> {
    return this.nextEvolutions?.map { nextEvolution ->
        NextEvolutionEntity(
            id = 0,
            pokemonId = this.id,
            name = nextEvolution.name,
            num = nextEvolution.num
        )
    } ?: emptyList()
}

fun PokemonDto.toPrevEvolutionEntities(): List<PrevEvolutionEntity> {
    return this.prevEvolutions?.map { prevEvolution ->
        PrevEvolutionEntity(
            id = 0,
            pokemonId = this.id,
            name = prevEvolution.name,
            num = prevEvolution.num
        )
    } ?: emptyList()
}

fun PokemonDto.toTypeEntities(): List<TypeEntity> {
    return this.types?.map { type ->
        TypeEntity(
            id = 0,
            pokemonId = this.id,
            value = type
        )
    } ?: emptyList()
}

fun PokemonDto.toWeaknessEntities(): List<WeaknessEntity> {
    return this.weaknesses?.map { weakness ->
        WeaknessEntity(
            id = 0,
            pokemonId = this.id,
            value = weakness
        )
    } ?: emptyList()
}

fun PokemonDto.toImageEntity(localUrl: String): ImageEntity {
    return ImageEntity(
        id = 0,
        pokemonId = this.id,
        localUrl = localUrl
    )
}