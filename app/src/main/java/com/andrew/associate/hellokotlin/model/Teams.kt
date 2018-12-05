package com.andrew.associate.hellokotlin.model

import com.google.gson.annotations.SerializedName

data class Teams(

    @SerializedName("idLeague")
    var idLeague: String,
    @SerializedName("idTeam")
    var idTeam: String,
    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String,
    @SerializedName("strLeague")
    var strLeague: String,
    @SerializedName("strTeam")
    var strTeam: String,
    @SerializedName("strTeamBadge")
    var strTeamBadge: String,
    @SerializedName("strTeamBanner")
    var strTeamBanner: String,
    @SerializedName("strTeamLogo")
    var strTeamLogo: String
)
