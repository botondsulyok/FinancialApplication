package com.example.financialapplication.domain

import co.zsmb.rainbowcake.withIOContext
import com.example.financialapplication.data.Categories
import com.example.financialapplication.data.Result
import com.example.financialapplication.data.ResultSuccess
import com.example.financialapplication.data.cache.CacheItemDataSource
import com.example.financialapplication.data.network.NetworkItemsDataSource
import com.example.financialapplication.ui.items.models.UiItem
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap

@Singleton
class ItemsInteractor @Inject constructor(
    private val networkItemsDataSource: NetworkItemsDataSource,
    private val cacheItemDataSource: CacheItemDataSource,
) {

    fun getItemsFromNetwork(): Result<List<UiItem>, String> {
        return networkItemsDataSource.getItemsMock()
    }

    fun getItemsFromCache(): Result<List<UiItem>, String> {
        return cacheItemDataSource.getItemsFromCache()
    }

    fun cacheItems(items: List<UiItem>) {
        cacheItemDataSource.cacheItems(items)
    }

    fun changeItem(item: UiItem) {
        cacheItemDataSource.changeItem(item)
    }

    fun searchItemsByTitleInCache(title: String): Result<List<UiItem>, String> {
        return cacheItemDataSource.searchItemsBySummary(title)
    }

    fun getDataForStats(): HashMap<String, Int> {
        val statValues = hashMapOf<String, Int>()
        val result = (getItemsFromCache() as? ResultSuccess) ?: return statValues
        result.value.groupBy { it.category }.forEach { groupedByCategory: Map.Entry<String?, List<UiItem>> ->
            statValues[groupedByCategory.key ?: Categories.MISCELLANEOUS.value] = groupedByCategory.value.sumOf { it.sum ?: 0 }
        }
        return statValues
    }
}