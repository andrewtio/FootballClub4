package com.andrew.associate.hellokotlin.model

import io.reactivex.Flowable

interface GameEventView{
    fun getLookUpTeam(id: String = "0") : Flowable<TeamResponses>

    fun getEventPastLeague(id:String) : Flowable<GameResponse>

    fun getEventNextLeague(id:String) :Flowable<GameResponse>
}