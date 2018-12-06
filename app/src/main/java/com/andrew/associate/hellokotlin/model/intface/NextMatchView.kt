package com.andrew.associate.hellokotlin.model.intface

import com.andrew.associate.hellokotlin.model.GameItems

interface NextMatchView {
    interface View{
        fun hideProgress()
        fun showProgress()
        fun displayGame(gameList:List<GameItems>)

    }

    interface Presenter{
        fun getGameNextItem()

    }
}