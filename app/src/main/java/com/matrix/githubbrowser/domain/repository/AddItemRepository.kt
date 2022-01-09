package com.matrix.githubbrowser.domain.repository

import androidx.lifecycle.LiveData
import com.matrix.githubbrowser.data.models.ItemsEntity
import com.matrix.githubbrowser.domain.dao.ItemsDao
import javax.inject.Inject

class AddItemRepository @Inject constructor(
    private val itemsDao: ItemsDao
) {

    suspend fun saveItem(itemsEntity: ItemsEntity) = itemsDao.saveItem(itemsEntity)

    suspend fun removeItem(itemsEntity: ItemsEntity) = itemsDao.removeItem(itemsEntity)

    fun getAllSavedRepos(): LiveData<List<ItemsEntity>> = itemsDao.getAllSavedRepos()


}