package com.example.financialapplication.data.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "items")
data class RoomItem(
    @PrimaryKey(autoGenerate = false) val id: String,
    val summary: String? = null,
    val category: String? = null,
    val sum: Int? = null,
    val currency: String? = null,
    val paid: String? = null
)