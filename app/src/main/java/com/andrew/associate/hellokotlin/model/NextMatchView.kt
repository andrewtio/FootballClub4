package com.andrew.associate.hellokotlin.model

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