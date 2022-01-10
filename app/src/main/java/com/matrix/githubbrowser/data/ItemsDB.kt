package com.matrix.githubbrowser.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matrix.githubbrowser.domain.room.dao.ItemsDao
import com.matrix.githubbrowser.data.models.ItemsEntity

@Database(entities = [ItemsEntity::class], version = 1)
abstract class ItemsDB : RoomDatabase() {

    abstract fun itemsDao(): ItemsDao

}