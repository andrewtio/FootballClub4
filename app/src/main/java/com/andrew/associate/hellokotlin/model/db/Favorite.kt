package com.andrew.associate.hellokotlin.model.db

class Favorite (val id: Long?,
                val gameId: String,
                val homeTeamId: String,
                val awayTeamId: String) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val GAME_ID: String = "GAME_ID"
        const val HOME_CLUB_ID: String = "HOME_CLUB"
        const val AWAY_CLUB_ID: String = "AWAY_CLUB"
    }
}