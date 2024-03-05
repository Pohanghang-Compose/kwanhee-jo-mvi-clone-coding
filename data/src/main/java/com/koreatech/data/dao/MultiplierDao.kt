package com.koreatech.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.koreatech.data.entity.MultiplierEntity

@Dao
interface MultiplierDao {
    @Insert
    suspend fun insertAll(multipliers: List<MultiplierEntity>)
}