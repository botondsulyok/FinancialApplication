package com.example.financialapplication.ui.items

import co.zsmb.rainbowcake.withIOContext
import com.example.financialapplication.data.*
import com.example.financialapplication.domain.ItemsInteractor
import com.example.financialapplication.ui.items.formatters.ItemsFormatter
import com.example.financialapplication.ui.items.models.UiItem
import java.util.*
import javax.inject.Inject

class ItemsPresenter @Inject constructor(
    private val itemsInteractor: ItemsInteractor,
    private val itemsFormatter: ItemsFormatter
) {

    suspend fun getItemsFromNetwork(): Result<List<UiItem>, String> = withIOContext {
        handleItemsArrivedInteractorResponse(itemsInteractor.getItemsFromNetwork())
    }

    suspend fun getItemsFromCache(): Result<List<UiItem>, String> = withIOContext {
        handleItemsArrivedInteractorResponse(itemsInteractor.getItemsFromCache())
    }

    suspend fun cacheItems(items: List<UiItem>) = withIOContext {
        itemsInteractor.cacheItems(items)
    }

    suspend fun changeItem(item: UiItem) = withIOContext {
        itemsInteractor.changeItem(item)
    }

    suspend fun searchItemsByTitleInCache(title: String): Result<List<UiItem>, String> = withIOContext {
        handleItemsArrivedInteractorResponse(itemsInteractor.searchItemsByTitleInCache(title))
    }

    suspend fun getDataForStats(): HashMap<String, Int> = withIOContext {
        itemsInteractor.getDataForStats()
    }

    private fun handleItemsArrivedInteractorResponse(result: Result<List<UiItem>, String>): Result<List<UiItem>, String> {
        return when (result) {
            is ResultSuccess -> ResultSuccess(itemsFormatter.formatItems(result.value))
            is ResultFailure -> result
        }
    }
}