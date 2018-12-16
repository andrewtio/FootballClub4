package com.andrew.associate.hellokotlin.presenter

import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.model.response.GameResponse
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.model.intface.GameEventView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameEventPresenter(private val gEV: GameEventView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson){

    fun getGamePrevData(competition: String?){

//        Log.d("debug","competition :" + competition)

        GlobalScope.launch(Dispatchers.Main){
            val dataGame = gson.fromJson(apiRepository
                .doRequest(ApiRestInterface.getEventsPastLeague(competition)).await()
                ,GameResponse::class.java
            )
//            Log.d("Debug", "data log: " + dataGame)

            gEV.showItemGameList(dataGame.games)


        }
    }

    fun getGameNextData(competition: String?) {

        GlobalScope.launch(Dispatchers.Main){
            val dataGame = gson.fromJson( apiRepository
                .doRequest(ApiRestInterface.getEventsNextLeague(competition)).await()
                , GameResponse::class.java )

//            Log.d("Debug", "data log: " + dataGame)
            gEV.showItemGameList(dataGame.games)
        }
    }
}