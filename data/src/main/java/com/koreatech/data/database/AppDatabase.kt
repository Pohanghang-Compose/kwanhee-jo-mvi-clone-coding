package com.koreatech.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.koreatech.data.dao.ImageDao
import com.koreatech.data.dao.PokemonDao
import com.koreatech.data.dao.MultiplierDao
import com.koreatech.data.dao.NextEvolutionDao
import com.koreatech.data.dao.PrevEvolutionDao
import com.koreatech.data.dao.TypeDao
import com.koreatech.data.dao.WeaknessDao
import com.koreatech.data.entity.ImageEntity
import com.koreatech.data.entity.MultiplierEntity
import com.koreatech.data.entity.NextEvolutionEntity
import com.koreatech.data.entity.PokemonEntity
import com.koreatech.data.entity.PrevEvolutionEntity
import com.koreatech.data.entity.TypeEntity
import com.koreatech.data.entity.WeaknessEntity


@Database(
    entities = [
        PokemonEntity::class,
        MultiplierEntity::class,
        NextEvolutionEntity::class,
        PrevEvolutionEntity::class,
        TypeEntity::class,
        WeaknessEntity::class,
        ImageEntity::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
    abstract fun getMultiplierDao(): MultiplierDao
    abstract fun getNextEvolutionDao(): NextEvolutionDao
    abstract fun getPrevEvolutionDao(): PrevEvolutionDao
    abstract fun getTypeDao(): TypeDao
    abstract fun getWeaknessDao(): WeaknessDao
    abstract fun getImageDao(): ImageDao
}