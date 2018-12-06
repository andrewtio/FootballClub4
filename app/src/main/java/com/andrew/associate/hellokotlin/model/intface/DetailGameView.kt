package com.andrew.associate.hellokotlin.model.intface

import com.andrew.associate.hellokotlin.model.GameItems
import com.andrew.associate.hellokotlin.model.Teams
import com.andrew.associate.hellokotlin.model.db.Favorite

interface DetailGameView {

    interface View{
        fun showClubLogoHome(team: Teams)
        fun showClubLogoAway(team: Teams)
        fun setFavState(favList:List<Favorite>)
    }

    interface Presenter{
        fun getClubLogoAway(id:String)
        fun getClubLogoHome(id:String)
        fun removeFromFav(id:String)
        fun favState(id:String)
        fun addToFav(gameId: String, homeClubId: String, awayClubId: String)
    }

}