package com.strelkovax.catsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.strelkovax.catsapp.domain.entity.CatItem

@Dao
interface CatInfoDao {
    @Query("SELECT * FROM cat_info_list")
    fun getFavoriteCatList(): LiveData<List<CatItem>>

    @Query("SELECT * FROM cat_info_list WHERE id == :id LIMIT 1")
    fun getInfoAboutCat(id: String): LiveData<CatItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatList(catList: List<CatItem>)
}