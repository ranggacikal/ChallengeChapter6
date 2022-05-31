package com.ranggacikal.challengechapter5.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class History(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "name_player") val namePlayer: String,
    @ColumnInfo(name = "result_match") val resultMatch: String,
    @ColumnInfo(name = "date") val date: String
): Parcelable