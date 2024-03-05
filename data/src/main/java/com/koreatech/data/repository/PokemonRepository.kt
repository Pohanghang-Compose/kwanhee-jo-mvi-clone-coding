package com.koreatech.data.repository

import android.util.Log
import com.koreatech.data.dao.ImageDao
import com.koreatech.data.dao.MultiplierDao
import com.koreatech.data.dao.NextEvolutionDao
import com.koreatech.data.dao.PokemonDao
import com.koreatech.data.dao.PrevEvolutionDao
import com.koreatech.data.dao.TypeDao
import com.koreatech.data.dao.WeaknessDao
import com.koreatech.data.database.AppDatabase
import com.koreatech.data.datasource.PokemonDataSource
import com.koreatech.data.entity.PokemonDetails
import com.koreatech.data.utils.mapper.toImageEntity
import com.koreatech.data.utils.mapper.toMultiplierEntities
import com.koreatech.data.utils.mapper.toNextEvolutionEntities
import com.koreatech.data.utils.mapper.toPokemonEntity
import com.koreatech.data.utils.mapper.toPrevEvolutionEntities
import com.koreatech.data.utils.mapper.toTypeEntities
import com.koreatech.data.utils.mapper.toWeaknessEntities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream

class PokemonRepository(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonDao: PokemonDao,
    private val multiplierDao: MultiplierDao,
    private val nextEvolutionDao: NextEvolutionDao,
    private val prevEvolutionDao: PrevEvolutionDao,
    private val typeDao: TypeDao,
    private val weaknessDao: WeaknessDao,
    private val imageDao: ImageDao,
    private val imageClient: OkHttpClient,
    private val imageDirectory: String,
    private val appDatabase: AppDatabase,
) {
    suspend fun fetch(): Boolean {
        try {
            pokemonDataSource.fetchData().forEach { pokemonDto ->
                pokemonDao.insert(pokemonDto.toPokemonEntity())
                multiplierDao.insertAll(pokemonDto.toMultiplierEntities())
                nextEvolutionDao.insertAll(pokemonDto.toNextEvolutionEntities())
                prevEvolutionDao.insertAll(pokemonDto.toPrevEvolutionEntities())
                typeDao.insertAll(pokemonDto.toTypeEntities())
                weaknessDao.insertAll(pokemonDto.toWeaknessEntities())
                withContext(Dispatchers.IO) {
                    val localUrl = downloadImage(pokemonDto.id, pokemonDto.img)
                    if (localUrl != null) imageDao.insert(pokemonDto.toImageEntity(localUrl))
                }
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun clear() {
        appDatabase.clearAllTables()
    }

    suspend fun getAll(): List<PokemonDetails> {
        return try {
            pokemonDao.getAll()
        } catch (e: Exception) {
            Log.e("aaa", "e : ${e.message}")
            pokemonDao.getAll()
        }
    }

    suspend fun getById(id: Int): PokemonDetails? {
        return pokemonDao.getById(id)
    }

    suspend fun getByNumbers(numbers: List<String>): List<PokemonDetails> {
        return pokemonDao.getByNumbers(numbers)
    }

    private fun downloadImage(id: Int, remoteUrl: String?): String? {
        if (remoteUrl == null) return null

        val request = Request.Builder().url(remoteUrl).build()
        val response = imageClient.newCall(request).execute()
        val imageBytes = response.body?.bytes() ?: return null
        val file = File(imageDirectory, "$id.png")
        FileOutputStream(file).apply {
            write(imageBytes)
            close()
        }

        return file.absolutePath
    }
}