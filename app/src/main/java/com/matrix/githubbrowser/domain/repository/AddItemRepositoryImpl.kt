package com.matrix.githubbrowser.domain.repository

import com.matrix.githubbrowser.domain.dao.ItemsDao
import com.matrix.githubbrowser.data.models.ItemsEntity
import com.matrix.githubbrowser.domain.repository.AddItemRepository

class AddItemRepositoryImpl(
    private val dao: ItemsDao
): AddItemRepository {
    override suspend fun saveItem(itemsEntity: ItemsEntity) {
        return dao.saveItem(itemsEntity)
    }

    override suspend fun removeItem(itemsEntity: ItemsEntity) {
        return dao.removeItem(itemsEntity)
    }

    override fun getAllSavedRepos(): List<ItemsEntity> {
        return dao.getAllSavedRepos()
    }

}