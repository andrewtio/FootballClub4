package com.andrew.associate.hellokotlin.model.intface

import com.andrew.associate.hellokotlin.model.GameDataItems
import com.andrew.associate.hellokotlin.model.response.GameResponse
import com.andrew.associate.hellokotlin.model.response.TeamResponses
import io.reactivex.Flowable

interface GameEventView{
    fun showLoading()
    fun hideLoading()
    fun showItemGameList(item: List<GameDataItems>)
}