package com.andrew.associate.hellokotlin.presenter

import android.util.Log
import com.andrew.associate.hellokotlin.model.GameDataItems
import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.model.response.GameResponse
import com.andrew.associate.hellokotlin.model.response.TeamResponses
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.model.intface.GameEventView
import com.google.gson.Gson
import io.reactivex.Flowable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class GameEventPresenter(private val gEV: GameEventView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson){

    fun getGamePrevData(competition: String?){

        Log.d("debug","competition :" + competition)

        doAsync {
            val dataGame = gson.fromJson(apiRepository
                .doRequest(ApiRestInterface.getEventsPastLeague(competition))
                ,GameResponse::class.java
            )
            Log.d("Debug", "data log: " + dataGame)

            uiThread {
                gEV.showItemGameList(dataGame.games)
            }

        }
    }

    fun getGameNextData(competition: String?) {
        doAsync {
            val dataGame = gson.fromJson( apiRepository
                .doRequest(ApiRestInterface.getEventsNextLeague(competition))
                , GameResponse::class.java )

            Log.d("Debug", "data log: " + dataGame)
            uiThread { gEV.showItemGameList(dataGame.games) }
        }
    }
}