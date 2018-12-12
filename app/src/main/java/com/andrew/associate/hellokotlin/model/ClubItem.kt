package com.andrew.associate.hellokotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClubItem (val clubName: String,
                     val clubImage: Int,
                     val clubDescription: String) : Parcelable