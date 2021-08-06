package com.strelkovax.catsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.strelkovax.catsapp.domain.entity.CatItem

@Dao
interface CatInfoDao {
    @Query("SELECT * FROM cat_info_list")
    fun getFavoriteCatList(): LiveData<List<CatItem>>

    @Query("SELECT * FROM cat_info_list WHERE id == :id LIMIT 1")
    fun getInfoAboutCatById(id: String): LiveData<CatItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatItem(catItem: CatItem)

    @Delete
    fun deleteCatItem(catItem: CatItem)
}