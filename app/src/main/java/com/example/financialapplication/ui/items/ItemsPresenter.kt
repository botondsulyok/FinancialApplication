package com.example.financialapplication.ui.items

import co.zsmb.rainbowcake.withIOContext
import com.example.financialapplication.data.*
import com.example.financialapplication.domain.ItemsInteractor
import com.example.financialapplication.ui.items.formatters.ItemsFormatter
import com.example.financialapplication.ui.items.models.UiItem
import java.text.DecimalFormat
import javax.inject.Inject

class ItemsPresenter @Inject constructor(
    private val itemsInteractor: ItemsInteractor,
    private val itemsFormatter: ItemsFormatter
) {

    suspend fun getItemsFromNetwork(): Result<List<UiItem>, String> = withIOContext {
        handleInteractorResponse(itemsInteractor.getItemsFromNetwork())
    }

    suspend fun getItemsFromCache(): Result<List<UiItem>, String> = withIOContext {
        handleInteractorResponse(itemsInteractor.getItemsFromCache())
    }

    suspend fun cacheItems(items: List<UiItem>) = withIOContext {
        itemsInteractor.cacheItems(items)
    }

    suspend fun searchItemsByTitleInCache(title: String): Result<List<UiItem>, String> = withIOContext {
        handleInteractorResponse(itemsInteractor.searchItemsByTitleInCache(title))
    }

    private fun handleInteractorResponse(result: Result<List<UiItem>, String>): Result<List<UiItem>, String> {
        return when (result) {
            is ResultSuccess -> ResultSuccess(itemsFormatter.formatItems(result.value))
            is ResultFailure -> result
        }
    }
}