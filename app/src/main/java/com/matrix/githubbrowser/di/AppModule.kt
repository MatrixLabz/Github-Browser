package com.matrix.githubbrowser.di

import android.content.Context
import androidx.room.Room
import com.matrix.githubbrowser.data.ItemsDB
import com.matrix.githubbrowser.domain.repository.AddItemRepository
import com.matrix.githubbrowser.domain.repository.AddItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

//    @Provides
//    fun provideItemDao(itemsDB: ItemsDB): ItemsDao {
//        return itemsDB.itemsDao()
//    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ItemsDB {
        return Room.databaseBuilder(
            appContext,
            ItemsDB::class.java,
            "repo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemsRepository(db: ItemsDB): AddItemRepository {
        return AddItemRepositoryImpl(db.itemsDao())
    }

}