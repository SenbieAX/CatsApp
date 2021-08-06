package com.strelkovax.catsapp.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cat_info_list")
data class CatItem (
    @PrimaryKey
    @SerializedName("id") var id : String,
    @SerializedName("url") var url : String,
    @SerializedName("width") var width : Int,
    @SerializedName("height") var height : Int
)