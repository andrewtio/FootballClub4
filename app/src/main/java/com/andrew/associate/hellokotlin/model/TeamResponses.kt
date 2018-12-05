package com.andrew.associate.hellokotlin.model

import com.google.gson.annotations.SerializedName

data class TeamResponses(
    @SerializedName("teams")
    var teams: List<Teams>
)