package com.andrew.associate.hellokotlin.model.intface

import com.andrew.associate.hellokotlin.model.GameDetailDataItems

interface FavGameView {
    interface View{
        fun showProgress()
        fun hideProgress()
        fun showFavGame(gameDetailDataList: List<GameDetailDataItems>)
        fun stealthSwipe()
    }

    interface Presenter{
        fun getGameItem()
    }
}