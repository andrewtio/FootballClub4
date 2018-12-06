package com.andrew.associate.hellokotlin.model.response

import com.andrew.associate.hellokotlin.model.Teams
import com.google.gson.annotations.SerializedName

data class TeamResponses(
    @SerializedName("teams")
    var teams: List<Teams>
)