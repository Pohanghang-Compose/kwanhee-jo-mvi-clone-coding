package com.koreatech.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.koreatech.data.entity.PrevEvolutionEntity

@Dao
interface PrevEvolutionDao {
    @Insert
    suspend fun insertAll(prevEvolutions: List<PrevEvolutionEntity>)
}
