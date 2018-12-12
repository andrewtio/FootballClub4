package com.andrew.associate.hellokotlin.model

import com.google.gson.annotations.SerializedName

data class ImageDataItem(
    @SerializedName("strTeamBadge")
    val mTeamBadge: String? = ""
)