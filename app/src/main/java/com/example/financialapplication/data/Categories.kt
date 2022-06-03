package com.example.financialapplication.data

enum class Categories(val value: String) {
    HOUSING("housing"),
    TRAVEL("travel"),
    FOOD("food"),
    UTILITIES("utilities"),
    INSURANCE("insurance"),
    HEALTH_CARE("healthcare"),
    FINANCIAL("financial"),
    LIFESTYLE("lifestyle"),
    ENTERTAINMENT("entertainment"),
    MISCELLANEOUS("miscellaneous");

    companion object {
        fun fromValue(valueKey: String?): Categories? = values().firstOrNull { it.value == valueKey }
    }
}