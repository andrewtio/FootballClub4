package com.andrew.associate.hellokotlin.model

import com.google.gson.annotations.SerializedName

data class GameResponse (
    @SerializedName("events") var events: List<GameItems>
)