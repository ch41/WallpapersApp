package com.example.data.db.searchdb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "search_db")
data class SearchDb(
    @PrimaryKey
    val searchQuery: String,
    val resultAmount: Long,
    val searchDate: String,
    var isFavorite: Boolean = false

) {
}