package com.matrix.githubbrowser.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "repo_table")
data class ItemsEntity(

    val repoName: String,
    val repoOwner: String,
    val repoDescription: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = -1

    ) {

}
