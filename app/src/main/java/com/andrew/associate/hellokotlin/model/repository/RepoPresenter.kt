package com.andrew.associate.hellokotlin.model.repository

import android.content.Context
import com.andrew.associate.hellokotlin.model.db.Favorite
import com.andrew.associate.hellokotlin.model.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class RepoPresenter(private val ctx: Context) : Repo {

    override fun getGameDb(): List<Favorite> {
        lateinit var favList : List<Favorite>
        ctx.database.use {
            val output = select(Favorite.TABLE_FAVORITE)
            val fav = output.parseList(classParser<Favorite>())
            favList = fav
        }
        return favList
    }


    override fun addFavItem(gameId:String, homeClubId: String, awayClubId: String){
        ctx.database.use{
            insert(Favorite.TABLE_FAVORITE,
                Favorite.GAME_ID to gameId,
                Favorite.HOME_CLUB_ID to homeClubId,
                Favorite.AWAY_CLUB_ID to awayClubId)
        }
    }

    override fun removeFavItem(gameId: String){
        ctx.database.use{
            delete(Favorite.TABLE_FAVORITE, "(GAME_ID = {id})",
                "id" to gameId)
        }
    }

    override fun scanFav(gameId: String): List<Favorite>{
        return ctx.database.use{
            val output = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(GAME_ID = {id})",
                    "id" to gameId)
            val fav = output.parseList(classParser<Favorite>())
            fav
        }
    }
}