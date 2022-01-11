package com.matrix.githubbrowser.domain.api

import com.matrix.githubbrowser.data.models.api.GetIssuesModel
import com.matrix.githubbrowser.data.models.api.GetRepoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {

    @GET
    suspend fun getRepo(@Url url: String): Response<GetRepoModel>

    @GET
    suspend fun getIssues(@Url url: String): Response<MutableList<GetIssuesModel>>

}