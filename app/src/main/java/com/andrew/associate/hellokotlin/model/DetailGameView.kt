package com.andrew.associate.hellokotlin.model

interface DetailGameView {

    interface View{
        fun showClubLogoHome(team: Teams)
        fun showClubLogoAway(team: Teams)
    }

    interface Presenter{
        fun getClubLogoAway(id:String)
        fun getClubLogoHome(id:String)
    }

}