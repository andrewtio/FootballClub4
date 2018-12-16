package com.andrew.associate.hellokotlin.presenter

import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.model.intface.DetailGameView
import com.andrew.associate.hellokotlin.model.response.DetailGameResponse
import com.andrew.associate.hellokotlin.model.response.ImageResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class GameDetailPresenter (val dGView : DetailGameView,
                           val apiRepository: ApiRepository,
                           val gson: Gson) {

    fun getClubImage ( club: String?, clubType: String?){

        GlobalScope.launch(Dispatchers.Main){
            val dataClub = gson.fromJson(apiRepository
                .doRequest(ApiRestInterface.getImageClub(club)).await()
                , ImageResponse::class.java)


            if(clubType == "Away")
                dGView.showAwayClubImage(dataClub.teams)
            else
                dGView.showHomeClubImage(dataClub.teams)

        }
    }

    fun getGameDetail (game: String?){

        GlobalScope.launch(Dispatchers.Main){
            val dataDetail = gson.fromJson ( apiRepository.doRequest(
                ApiRestInterface.getLookupEvent(game)).await()
                , DetailGameResponse::class.java)


            dGView.showDetailGame(dataDetail.events)

        }
    }
}