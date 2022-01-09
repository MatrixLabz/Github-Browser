package com.matrix.githubbrowser.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.matrix.githubbrowser.data.models.ItemsEntity

@Dao
interface ItemsDao {

    @Insert
    suspend fun saveItem(itemsEntity: ItemsEntity)

    @Delete
    suspend fun removeItem(itemsEntity: ItemsEntity)

    @Query("select * from repo_details")
    fun getAllSavedRepos(): List<ItemsEntity>

}