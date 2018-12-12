package com.andrew.associate.hellokotlin.model.intface

import com.andrew.associate.hellokotlin.model.GameDetailDataItems

interface NextMatchView {
    interface View{
        fun hideProgress()
        fun showProgress()
        fun displayGame(gameDetailDataList:List<GameDetailDataItems>)

    }

    interface Presenter{
        fun getGameNextItem()

    }
}