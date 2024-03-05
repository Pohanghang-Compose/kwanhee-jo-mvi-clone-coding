package com.koreatech.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.koreatech.data.entity.NextEvolutionEntity

@Dao
interface NextEvolutionDao {
    @Insert
    suspend fun insertAll(nextEvolutions: List<NextEvolutionEntity>)
}