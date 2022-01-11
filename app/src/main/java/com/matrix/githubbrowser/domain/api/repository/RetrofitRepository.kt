package com.matrix.githubbrowser.domain.api.repository

import com.matrix.githubbrowser.domain.api.ApiService
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getRepo(ownerName: String, repoName: String) = apiService.getRepo("$ownerName/$repoName")

    suspend fun getIssue(ownerName: String, repoName: String) = apiService.getIssues("$ownerName/$repoName/issues?state=open")

    suspend fun getBranch(ownerName: String, repoName: String) = apiService.getBranches("$ownerName/$repoName/branches")

}