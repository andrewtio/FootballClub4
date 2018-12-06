package com.andrew.associate.hellokotlin.model.intface

import com.andrew.associate.hellokotlin.model.response.GameResponse
import com.andrew.associate.hellokotlin.model.response.TeamResponses
import io.reactivex.Flowable

interface GameEventView{
    fun getLookUpTeam(id: String = "0") : Flowable<TeamResponses>

    fun getEventPastLeague(id:String) : Flowable<GameResponse>

    fun getEventNextLeague(id:String) :Flowable<GameResponse>
}