package com.andrew.associate.hellokotlin.presenter

import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.model.intface.DetailGameView
import com.andrew.associate.hellokotlin.model.response.DetailGameResponse
import com.andrew.associate.hellokotlin.model.response.ImageResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class GameDetailPresenter (public val dGView : DetailGameView,
                           public val apiRepository: ApiRepository,
                           public val gson: Gson) {

    fun getClubImage ( club: String?, clubType: String?){

        doAsync {
            val dataClub = gson.fromJson(apiRepository
                .doRequest(ApiRestInterface.getImageClub(club))
                , ImageResponse::class.java)

            uiThread{
                if(clubType == "Away")
                    dGView.showAwayClubImage(dataClub.teams)
                else
                    dGView.showHomeClubImage(dataClub.teams)
            }
        }
    }

    fun getGameDetail (game: String?){

        doAsync{
            val dataDetail = gson.fromJson ( apiRepository.doRequest(
                ApiRestInterface.getLookupEvent(game))
                , DetailGameResponse::class.java)

            uiThread{
                dGView.showDetailGame(dataDetail.events)
            }
        }
    }
}