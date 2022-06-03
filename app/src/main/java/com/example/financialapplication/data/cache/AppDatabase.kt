package com.example.financialapplication.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.financialapplication.data.cache.models.RoomItem

@Database(entities = [RoomItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

}