package com.andrew.associate.hellokotlin.model.intface

import com.andrew.associate.hellokotlin.model.GameItems

interface FavGameView {
    interface View{
        fun showProgress()
        fun hideProgress()
        fun showFavGame(gameList: List<GameItems>)
    }

    interface Presenter{
        fun getGameItem()
    }
}