package com.matrix.githubbrowser.data.models.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class GetIssueSubModel(

    @SerializedName("login")
    @Expose
    val login: String,

    @SerializedName("avatar_url")
    @Expose
    val avatarUrl: String

)