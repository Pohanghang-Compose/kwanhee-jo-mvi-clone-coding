package com.koreatech.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.koreatech.data.entity.ImageEntity

@Dao
interface ImageDao {
    @Insert
    suspend fun insert(image: ImageEntity)
}
