package com.matrix.githubbrowser.di

import android.content.Context
import androidx.room.Room
import com.matrix.githubbrowser.data.ItemsDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ItemsDB =
        Room.databaseBuilder(
            appContext,
            ItemsDB::class.java,
            "repo_db"
        ).build()


    @Provides
    @Singleton
    fun provideItemsDao(db: ItemsDB) = db.itemsDao()

}