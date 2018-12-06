package com.andrew.associate.hellokotlin.model.repository

import com.andrew.associate.hellokotlin.model.db.Favorite

interface Repo {

    fun getGameDb(): List<Favorite>

    fun addFavItem(gameId: String, homeClubId: String, awayClubId: String)

    fun removeFavItem(gameId: String)

    fun scanFav(gameId: String): List<Favorite>

}