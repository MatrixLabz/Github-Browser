package com.matrix.githubbrowser.domain.room.repository

import androidx.lifecycle.LiveData
import com.matrix.githubbrowser.data.models.room.ItemsEntity
import com.matrix.githubbrowser.domain.room.dao.ItemsDao
import javax.inject.Inject

class AddItemRepository @Inject constructor(
    private val itemsDao: ItemsDao
) {

    suspend fun saveItem(itemsEntity: ItemsEntity) = itemsDao.saveItem(itemsEntity)

    suspend fun removeItem(itemsEntity: ItemsEntity) = itemsDao.removeItem(itemsEntity)

    fun getAllSavedRepos(): LiveData<List<ItemsEntity>> = itemsDao.getAllSavedRepos()


}