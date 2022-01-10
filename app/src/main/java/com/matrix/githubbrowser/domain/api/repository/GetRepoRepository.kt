package com.matrix.githubbrowser.domain.api.repository

import com.matrix.githubbrowser.domain.api.ApiService
import javax.inject.Inject

class GetRepoRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getRepo() = apiService.getRepo()

}