package com.matrix.githubbrowser.data.models.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetBranchesModel(

    @SerializedName("name")
    @Expose
    val branchName: String
)