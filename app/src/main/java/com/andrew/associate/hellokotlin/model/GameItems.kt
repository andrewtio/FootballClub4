package com.andrew.associate.hellokotlin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameItems(
    @SerializedName("dateEvent") var dateEvent: String? = null,
    @SerializedName("idAwayTeam") var idAwayTeam: String,
    @SerializedName("idEvent") var idEvent: String?,
    @SerializedName("idHomeTeam") var idHomeTeam: String,
    @SerializedName("idLeague") var idLeague: String?,
    @SerializedName("intAwayScore") var intAwayScore: String?,
    @SerializedName("intHomeScore") var intHomeScore: String?,
    @SerializedName("strAwayFormation") var strAwayFormation: String?,
    @SerializedName("strAwayGoalDetails") var strAwayGoalDetails: String?,
    @SerializedName("intAwayShots") var intAwayShots: String?,
    @SerializedName("strAwayLineupDefense") var strAwayLineupDefense: String?,
    @SerializedName("strAwayLineupForward") var strAwayLineupForward: String?,
    @SerializedName("strAwayLineupGoalkeeper") var strAwayLineupGoalkeeper: String?,
    @SerializedName("strAwayLineupMidfield") var strAwayLineupMidfield: String?,
    @SerializedName("strAwayLineupSubstitutes") var strAwayLineupSubstitutes: String?,
    @SerializedName("strAwayTeam") var strAwayTeam: String?,
    @SerializedName("strDate") var strDate: String?,
    @SerializedName("strEvent") var strEvent: String?,
    @SerializedName("strHomeFormation") var strHomeFormation: String?,
    @SerializedName("strHomeGoalDetails") var strHomeGoalDetails: String?,
    @SerializedName("intHomeShots") var intHomeShots: String?,
    @SerializedName("strHomeLineupDefense") var strHomeLineupDefense: String?,
    @SerializedName("strHomeLineupForward") var strHomeLineupForward: String?,
    @SerializedName("strHomeLineupGoalkeeper") var strHomeLineupGoalkeeper: String?,
    @SerializedName("strHomeLineupMidfield") var strHomeLineupMidfield: String?,
    @SerializedName("strHomeLineupSubstitutes") var strHomeLineupSubstitutes: String?,
    @SerializedName("strHomeTeam") var strHomeTeam: String?,
    @SerializedName("strLeague") var strLeague: String?
) : Parcelable