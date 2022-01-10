package com.matrix.githubbrowser.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.githubbrowser.data.models.ItemsEntity
import com.matrix.githubbrowser.domain.room.repository.AddItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AddItemRepository
): ViewModel() {

    val itemsEntities = repository.getAllSavedRepos()

    fun saveItem(itemsEntity: ItemsEntity) = viewModelScope.launch {
        repository.saveItem(itemsEntity)
    }

    fun removeItem(itemsEntity: ItemsEntity) = viewModelScope.launch {
        repository.removeItem(itemsEntity)
    }

}