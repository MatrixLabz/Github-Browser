package com.matrix.githubbrowser.domain.api

import com.matrix.githubbrowser.data.models.GetRepoModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("region/europe")
    suspend fun getRepo(): Response<GetRepoModel>


}