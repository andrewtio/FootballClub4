package com.andrew.associate.hellokotlin.model.api

import com.andrew.associate.hellokotlin.model.GameResponse
import com.andrew.associate.hellokotlin.model.TeamResponses
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRestInterface {

    @GET ("lookupteam.php")
    fun lookUpTeam (@Query("id") id:String) : Flowable<TeamResponses>

    @GET("eventspastleague.php")
    fun eventPastLeague(@Query("id") id:String) : Flowable<GameResponse>

    @GET("eventsnextleague.php")
    fun eventNextLeague(@Query("id") id:String) : Flowable<GameResponse>

}