package com.example.maith.sharinginternetaccess.roomdatabase.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Product")
data class Product(
        @PrimaryKey
        @ColumnInfo(name = "productID")
        @SerializedName("productID")
        val productId: String,

        @ColumnInfo(name = "productName")
        @SerializedName("productName")
        val productName: String,

        @ColumnInfo(name = "productThumb")
        @SerializedName("productThumb")
        val productThumb: Int

) : Serializable