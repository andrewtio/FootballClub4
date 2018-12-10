package com.andrew.associate.hellokotlin.model.support

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.andrew.associate.hellokotlin.model.db.Favorite
import com.andrew.associate.hellokotlin.model.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class SupportPresenter(private val ctx: Context) : Support {

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
        try {
            ctx.database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.GAME_ID to gameId,
                    Favorite.HOME_CLUB_ID to homeClubId,
                    Favorite.AWAY_CLUB_ID to awayClubId)
            }
        }catch (e: SQLiteConstraintException){
            
        }
    }

    override fun removeFavItem(gameId: String){
        try {
            ctx.database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(GAME_ID = {id})",
                    "id" to gameId)
            }
        }catch (e:SQLiteConstraintException){

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