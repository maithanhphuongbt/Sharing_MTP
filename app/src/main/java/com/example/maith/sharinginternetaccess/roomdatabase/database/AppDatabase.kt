package com.example.maith.sharinginternetaccess.roomdatabase.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.maith.sharinginternetaccess.roomdatabase.dao.ProductDAO
import com.example.maith.sharinginternetaccess.roomdatabase.model.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDAO

    companion object {
        val DB_NAME = "MYDB.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: builDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun builDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
    }
}