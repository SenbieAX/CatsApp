package com.strelkovax.catsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.strelkovax.catsapp.domain.entity.CatItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CatInfoDao {
    @Query("SELECT * FROM cat_info_list")
    fun getFavoriteCatList(): Flow<List<CatItem>>

    @Query("SELECT * FROM cat_info_list WHERE id == :id LIMIT 1")
    fun getInfoAboutCatById(id: String): Flow<CatItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatItem(catItem: CatItem)

    @Delete
    fun deleteCatItem(catItem: CatItem)
}