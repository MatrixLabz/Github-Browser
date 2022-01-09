package com.matrix.githubbrowser.domain.repository

import com.matrix.githubbrowser.data.models.ItemsEntity

interface AddItemRepository {

    suspend fun saveItem(itemsEntity: ItemsEntity)
    suspend fun removeItem(itemsEntity: ItemsEntity)
    fun getAllSavedRepos(): List<ItemsEntity>

}