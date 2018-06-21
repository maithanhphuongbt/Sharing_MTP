package com.example.maith.sharinginternetaccess.roomdatabase.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.maith.sharinginternetaccess.roomdatabase.model.Product
import io.reactivex.Flowable

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: ArrayList<Product> )

    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM Product")
    fun getAllProducts(): Flowable<Product>
}