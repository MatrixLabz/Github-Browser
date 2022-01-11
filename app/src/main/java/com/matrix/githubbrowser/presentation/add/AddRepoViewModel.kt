package com.matrix.githubbrowser.presentation.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.githubbrowser.data.models.api.GetRepoModel
import com.matrix.githubbrowser.domain.api.repository.GetRepoRepository
import com.matrix.githubbrowser.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRepoViewModel @Inject constructor(
    private val getRepoRepository: GetRepoRepository
) : ViewModel() {

    private val _res = MutableLiveData<Resource<GetRepoModel>>()

    val res: LiveData<Resource<GetRepoModel>>
        get() = _res

    fun getRepo(
        ownerName: String,
        repoName: String
    ) = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        getRepoRepository.getRepo(ownerName, repoName).let {
            if (it.isSuccessful) {
                Log.d("TAG", "getRepo: Successful")
                _res.postValue(Resource.success(it.body()))
            } else {
                Log.d("TAG", "getRepo: Failure")
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}