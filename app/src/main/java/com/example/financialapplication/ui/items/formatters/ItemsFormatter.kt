package com.example.financialapplication.ui.items.formatters

import com.example.financialapplication.R
import com.example.financialapplication.data.Categories
import com.example.financialapplication.data.Categories.*
import com.example.financialapplication.data.extensions.empty
import com.example.financialapplication.ui.items.models.UiItem
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class ItemsFormatter @Inject constructor() {

    fun formatItems(itemsList: List<UiItem>): List<UiItem> {
        return itemsList.map {
            it.copy(
                formattedSum = "${it.sum?.toStringNoDecimal()} ${it.currency}",
                formattedPaid = it.paid?.let { date -> formatDate(date) },
                iconDrawable = getIconDrawableResFromCategoryName(it.category)
            )
        }.sortedByDescending { it.paid }
    }

    private fun Int.toStringNoDecimal(): String? {
        val decimalFormat = DecimalFormat("#,###.##")
        return decimalFormat.format(this)
    }

    private fun formatDate(d: String): String {
        return try {
            val offsetDateTime: OffsetDateTime = OffsetDateTime.parse(d)
            return offsetDateTime.format(DateTimeFormatter.ofPattern(UI_DATE_FORMAT))
        } catch (e: Exception) {
            String.empty
        }
    }

    private fun getIconDrawableResFromCategoryName(category: String?): Int {
        return when (Categories.fromValue(category)) {
            HOUSING -> R.drawable.ic_baseline_house_24
            TRAVEL -> R.drawable.ic_baseline_card_travel_24
            FOOD -> R.drawable.ic_baseline_fastfood_24
            INSURANCE -> R.drawable.ic_baseline_security_24
            UTILITIES -> R.drawable.ic_baseline_settings_24
            HEALTH_CARE -> R.drawable.ic_baseline_house_24
            FINANCIAL -> R.drawable.ic_baseline_attach_money_24
            LIFESTYLE -> R.drawable.ic_baseline_directions_run_24
            ENTERTAINMENT -> R.drawable.ic_baseline_live_tv_24
            MISCELLANEOUS -> R.drawable.ic_baseline_star_24
            else -> R.drawable.ic_baseline_image_24
        }
    }

    companion object {
        private const val UI_DATE_FORMAT = "yyyy.MM.dd."
    }
}
