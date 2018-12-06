package com.andrew.associate.hellokotlin.presenter

import com.andrew.associate.hellokotlin.model.response.GameResponse
import com.andrew.associate.hellokotlin.model.response.TeamResponses
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.model.intface.GameEventView
import io.reactivex.Flowable

class GameEventPresenter(private val apiRestInterface: ApiRestInterface) :
    GameEventView {
    override fun getLookUpTeam(id: String): Flowable<TeamResponses> = apiRestInterface.lookUpTeam(id)

    override fun getEventPastLeague(id: String): Flowable<GameResponse> = apiRestInterface.eventPastLeague(id)

    override fun getEventNextLeague(id: String): Flowable<GameResponse> = apiRestInterface.eventNextLeague(id)

    fun getLookUpEvent(id: String): Flowable<GameResponse> = apiRestInterface.lookUpEvent(id)
}