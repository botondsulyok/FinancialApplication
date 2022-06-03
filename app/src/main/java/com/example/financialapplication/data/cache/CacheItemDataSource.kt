package com.example.financialapplication.data.cache

import android.content.ClipData
import android.util.Log
import com.example.financialapplication.ItemsApplication
import com.example.financialapplication.data.*
import com.example.financialapplication.data.cache.models.RoomItem
import com.example.financialapplication.ui.items.models.UiItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheItemDataSource @Inject constructor() {

    val itemDao = ItemsApplication.db.itemDao()

    fun getItemsFromCache(): Result<List<UiItem>, String> {
        return try {
            val items = itemDao.getItems().map { it.toUiItem() }
            ResultSuccess(items)
        }
        catch (e: Exception) {
            ResultFailure(e.message.toString())
        }
    }

    fun cacheItems(items: List<UiItem>) {
        val roomItems = items.map { it.toRoomItem() }
        try {
            itemDao.deleteAll()
            itemDao.insertItems(roomItems)
        }
        catch (e: Exception) {
            Log.e("Cache Exception", e.message.toString())
        }
    }

    fun changeItem(item: UiItem) {
        val roomItems = item.toRoomItem()
        try {
            itemDao.updateItem(roomItems)
        }
        catch (e: Exception) {
            Log.e("Cache Exception", e.message.toString())
        }
    }

    fun searchItemsBySummary(title: String): Result<List<UiItem>, String> {
        return try {
            val items = itemDao.searchBySummary(title).map { it.toUiItem() }
            ResultSuccess(items)
        } catch (e: Exception) {
            ResultFailure(e.message.toString())
        }
    }

    private fun UiItem.toRoomItem(): RoomItem {
        return RoomItem(
            id = id,
            summary = summary,
            category = category,
            sum = sum,
            currency = currency,
            paid = paid
        )
    }

    private fun RoomItem.toUiItem(): UiItem {
        return UiItem(
            id = id,
            summary = summary,
            category = category,
            sum = sum,
            currency = currency,
            paid = paid
        )
    }
}