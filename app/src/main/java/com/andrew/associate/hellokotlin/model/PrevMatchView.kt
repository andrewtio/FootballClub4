package com.andrew.associate.hellokotlin.model

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