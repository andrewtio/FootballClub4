package com.andrew.associate.hellokotlin.model

import com.google.gson.annotations.SerializedName

data class GameDataItems(

    @SerializedName("idEvent")
    var IdEvent: String? = null,

    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null,

    @SerializedName("intHomeScore")
    var intHomeScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    val strHomeGoalDetails: String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null,

    @SerializedName("intAwayScore")
    var intAwayScore: String? = null,

    @SerializedName("strAwayGoalDetails")
    val strAwayGoalDetails: String? = null
)