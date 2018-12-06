package com.andrew.associate.hellokotlin.model.intface

import com.andrew.associate.hellokotlin.model.GameItems

interface PrevMatchView {
    interface View{
        fun hideProgress()
        fun showProgress()
        fun displayGame(gameList:List<GameItems>)

    }

    interface Presenter{
        fun getGamePrevItem()

    }
}