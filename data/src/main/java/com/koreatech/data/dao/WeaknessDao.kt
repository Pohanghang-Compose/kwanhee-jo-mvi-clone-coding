package com.koreatech.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.koreatech.data.entity.WeaknessEntity

@Dao
interface WeaknessDao {
    @Insert
    suspend fun insertAll(weaknesses: List<WeaknessEntity>)

}
