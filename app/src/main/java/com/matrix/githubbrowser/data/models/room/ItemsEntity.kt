package com.matrix.githubbrowser.data.models.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_table")
data class ItemsEntity(

    val repoName: String,
    val repoOwner: String,
    val repoDescription: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = -1

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(repoName)
        parcel.writeString(repoOwner)
        parcel.writeString(repoDescription)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemsEntity> {
        override fun createFromParcel(parcel: Parcel): ItemsEntity {
            return ItemsEntity(parcel)
        }

        override fun newArray(size: Int): Array<ItemsEntity?> {
            return arrayOfNulls(size)
        }
    }

}
