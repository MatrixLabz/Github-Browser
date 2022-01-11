package com.matrix.githubbrowser.presentation.details.branches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.githubbrowser.data.models.api.GetBranchesModel
import com.matrix.githubbrowser.data.models.api.GetIssuesModel
import com.matrix.githubbrowser.domain.api.repository.RetrofitRepository
import com.matrix.githubbrowser.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BranchViewModel @Inject constructor(
    private val retrofitRepository: RetrofitRepository
) : ViewModel() {

    private val _res = MutableLiveData<Resource<MutableList<GetBranchesModel>>>()

    val res: LiveData<Resource<MutableList<GetBranchesModel>>>
        get() = _res

    fun getBranch(
        ownerName: String,
        repoName: String
    ) = viewModelScope.launch {
        _res.postValue(Resource.loading(null))

        retrofitRepository.getBranch(ownerName, repoName).let {
            if (it.isSuccessful) {
                Log.d("TAG", "getBranch: Successful")
                _res.postValue(Resource.success(it.body()))
            } else {
                Log.d("TAG", "getBranch: Failure")
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}