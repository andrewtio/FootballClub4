package com.andrew.associate.hellokotlin.model.response

import com.andrew.associate.hellokotlin.model.GameItems
import com.google.gson.annotations.SerializedName

data class GameResponse (
    @SerializedName("events") var events: List<GameItems>
)