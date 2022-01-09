package com.matrix.githubbrowser.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "repo_details")
data class ItemsEntity(
    @PrimaryKey
    val repoName: String,
    val repoOwner: String
)
