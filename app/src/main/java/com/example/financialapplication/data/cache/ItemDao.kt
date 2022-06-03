package com.example.financialapplication.data.cache

import androidx.room.*
import com.example.financialapplication.data.cache.models.RoomItem

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    fun getItems(): List<RoomItem>

    @Query("DELETE FROM items")
    fun deleteAll()

    @Update
    fun updateItem(item: RoomItem)

    @Insert
    fun insertItems(items: List<RoomItem>)

    @Query("SELECT * FROM items WHERE summary like '%' || :t || '%'")
    fun searchBySummary(t: String): List<RoomItem>

}