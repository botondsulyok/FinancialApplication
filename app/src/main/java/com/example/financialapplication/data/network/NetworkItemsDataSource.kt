package com.example.financialapplication.data.network

import com.example.financialapplication.data.Result
import com.example.financialapplication.data.ResultSuccess
import com.example.financialapplication.data.network.models.ItemResponse
import com.example.financialapplication.ui.items.models.UiItem
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkItemsDataSource @Inject constructor(
    private val mockItemsApi: MockItemsApi
) {

    @OptIn(ExperimentalStdlibApi::class)
    fun getItemsMock(): Result<List<UiItem>, String> {
        val json = mockItemsApi.getItemsMock()
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<List<ItemResponse>> = moshi.adapter()
        val items = jsonAdapter.fromJson(json)
        return ResultSuccess(items?.map { it.toUiItem() } ?: listOf())
    }

    private fun ItemResponse.toUiItem(): UiItem {
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