package com.example.data.repository

import asSearchDb
import com.example.data.db.searchdb.SearchDao
import com.example.data.db.searchdb.SearchDb
import com.example.domain.model.dto.SearchEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDao: SearchDao
) {

    val searchItems = searchDao.getSearches()
    val favoriteItems = searchDao.getAllFavoritesSearches()

    suspend fun insertSearch(searchEntity: SearchEntity) {
        withContext(Dispatchers.IO) {
            searchDao.insertSearch(searchEntity.asSearchDb())
        }
    }

    suspend fun updateSearch(searchDb: SearchDb) {
        withContext(Dispatchers.IO) {
            searchDao.updateSearch(searchDb)
        }
    }
    suspend fun deleteSearch(searchDb: SearchDb) {
        withContext(Dispatchers.IO) {
            searchDao.deleteSearch(searchDb)
        }
    }
    suspend fun searchByQuery(query:String) : SearchDb {
        return withContext(Dispatchers.IO) {
            searchDao.searchByQuery(query)
        }
    }
}