package com.matrix.githubbrowser.data.models.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class GetRepoModel(

    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("open_issues")
    @Expose
    val openIssues: String
)