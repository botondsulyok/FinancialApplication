package com.example.financialapplication.ui.items

import com.example.financialapplication.ui.items.models.UiItem

sealed class ItemsViewState

object Loading : ItemsViewState()

data class Error(val errorMessage: String?) : ItemsViewState()

data class ItemsLoaded(val items: List<UiItem>) : ItemsViewState()