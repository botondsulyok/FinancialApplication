package com.example.financialapplication.ui.items

import androidx.annotation.StringRes
import co.zsmb.rainbowcake.base.QueuedOneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.example.financialapplication.data.ResultFailure
import com.example.financialapplication.data.ResultSuccess
import com.example.financialapplication.R
import com.example.financialapplication.ui.items.models.UiItem
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class ItemsViewModel @Inject constructor(
    private val itemsPresenter: ItemsPresenter
) : RainbowCakeViewModel<ItemsViewState>(Loading) {

    fun loadItems() = execute {
        viewState = Loading
        loadItemsFromCache()
        viewState = Loading
        loadItemsFromNetwork()
    }

    fun updateItems() = execute {
        viewState = Loading
        loadItemsFromNetwork()
    }
    fun changeItem(item: UiItem) = execute {
        // todo we should call the real api
        viewState = Loading
        itemsPresenter.changeItem(item)
        loadItemsFromCache()
    }

    private suspend fun loadItemsFromNetwork() {
        val result = itemsPresenter.getItemsFromNetwork()
        if (result is ResultSuccess) {
            val items = result.value
            viewState = ItemsLoaded(items)
            itemsPresenter.cacheItems(items)
        } else {
            postQueuedEvent(FailedToUpdateEvent(R.string.failed_to_update_message))
        }
    }

    private suspend fun loadItemsFromCache() {
        viewState = when (val result = itemsPresenter.getItemsFromCache()) {
            is ResultSuccess -> {
                if (result.value.isEmpty()) {
                    Loading
                } else {
                    ItemsLoaded(result.value)
                }
            }
            is ResultFailure -> {
                Error(result.reason)
            }
        }
    }

    fun searchItemsByTitle(title: String) = execute {
        val result = itemsPresenter.searchItemsByTitleInCache(title)
        viewState = when (result) {
            is ResultSuccess -> {
                ItemsLoaded(result.value)
            }
            is ResultFailure -> {
                Error(result.reason)
            }
        }
    }

    fun navigateToStatsScreen() = execute {
        postQueuedEvent(NavigateToStatsScreen(itemsPresenter.getDataForStats()))
    }

    data class FailedToUpdateEvent(@StringRes val messageRes: Int) : QueuedOneShotEvent
    data class NavigateToStatsScreen(val values: HashMap<String, Int>) : QueuedOneShotEvent
}