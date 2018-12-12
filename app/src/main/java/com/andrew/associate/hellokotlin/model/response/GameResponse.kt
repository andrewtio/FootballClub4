package com.andrew.associate.hellokotlin.model.response

import com.andrew.associate.hellokotlin.model.GameDataItems
import com.google.gson.annotations.SerializedName

data class GameResponse (
    @SerializedName("events") var games: List<GameDataItems>
)