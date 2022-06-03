package com.example.financialapplication.ui.items.models

import androidx.annotation.DrawableRes

data class UiItem(
    val id: String,
    val summary: String? = null,
    val category: String? = null,
    val sum: Int? = null,
    val currency: String? = null,
    val paid: String? = null,
    val formattedSum: String? = null,
    val formattedPaid: String? = null,
    @DrawableRes val iconDrawable: Int? = null
)
