package com.example.financialapplication.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemResponse(
    @Json(name = "id") val id: String = "",
    @Json(name = "summary") val summary: String? = null,
    @Json(name = "category") val category: String? = null,
    @Json(name = "sum") val sum: Int? = null,
    @Json(name = "currency") val currency: String? = null,
    @Json(name = "paid") val paid: String? = null
)