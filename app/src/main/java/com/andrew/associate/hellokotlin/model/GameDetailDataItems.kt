package com.andrew.associate.hellokotlin.model

import com.google.gson.annotations.SerializedName

data class GameDetailDataItems(
    @SerializedName("dateEvent") val dateEvent: String? = null,
    @SerializedName("idAwayTeam") val idAwayTeam: String? = null,
    @SerializedName("idEvent") val idEvent: String? = null,
    @SerializedName("idHomeTeam") val idHomeTeam: String? = null,
    @SerializedName("idLeague") val idLeague: String? = null,
    @SerializedName("intAwayScore") val intAwayScore: String? = null,
    @SerializedName("intHomeScore") val intHomeScore: String? = null,
    @SerializedName("strAwayFormation") val strAwayFormation: String? = null,
    @SerializedName("strAwayGoalDetails") val strAwayGoalDetails: String? = null,
    @SerializedName("intAwayShots") val intAwayShots: String? = null,
    @SerializedName("strAwayLineupDefense") val strAwayLineupDefense: String? = null,
    @SerializedName("strAwayLineupForward") val strAwayLineupForward: String? = null,
    @SerializedName("strAwayLineupGoalkeeper") val strAwayLineupGoalkeeper: String? = null,
    @SerializedName("strAwayLineupMidfield") val strAwayLineupMidfield: String? = null,
    @SerializedName("strAwayLineupSubstitutes") val strAwayLineupSubstitutes: String? = null,
    @SerializedName("strAwayTeam") val strAwayTeam: String? = null,
    @SerializedName("strDate") val strDate: String? = null,
    @SerializedName("strEvent") val strEvent: String? = null,
    @SerializedName("strHomeFormation") val strHomeFormation: String? = null,
    @SerializedName("strHomeGoalDetails") val strHomeGoalDetails: String? = null,
    @SerializedName("intHomeShots") val intHomeShots: String? = null,
    @SerializedName("strHomeLineupDefense") val strHomeLineupDefense: String? = null,
    @SerializedName("strHomeLineupForward") val strHomeLineupForward: String? = null,
    @SerializedName("strHomeLineupGoalkeeper") val strHomeLineupGoalkeeper: String? = null,
    @SerializedName("strHomeLineupMidfield") val strHomeLineupMidfield: String? = null,
    @SerializedName("strHomeLineupSubstitutes") val strHomeLineupSubstitutes: String? = null,
    @SerializedName("strHomeTeam") val strHomeTeam: String? = null,
    @SerializedName("strLeague") val strLeague: String? = null
)