package com.koreatech.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.koreatech.data.entity.TypeEntity

@Dao
interface TypeDao {
    @Insert
    suspend fun insertAll(types: List<TypeEntity>)
}
