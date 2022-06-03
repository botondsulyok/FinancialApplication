package com.example.financialapplication.domain

import com.example.financialapplication.data.Result
import com.example.financialapplication.data.cache.CacheItemDataSource
import com.example.financialapplication.data.network.NetworkItemsDataSource
import com.example.financialapplication.ui.items.models.UiItem
import javax.inject.Inject
import javax.inject.Singleton

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
}