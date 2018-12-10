package com.andrew.associate.hellokotlin.model.db

class Favorite (val id: Long?,
                val gameId: String,
                val homeClubId: String,
                val awayClubId: String) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val GAME_ID = "GAME_ID"
        const val HOME_CLUB_ID = "HOME_CLUB"
        const val AWAY_CLUB_ID = "AWAY_CLUB"
        const val DATE_GAME = "DATE_GAME"
        const val HOME_CLUB = "HOME_CLUB"
        const val HOME_POINT = "HOME_POINT"
        const val HOME_POINT_DETAIL = "HOME_POINT_DETAIL"
        const val AWAY_CLUB = "AWAY_CLUB"
        const val AWAY_POINT = "AWAY_POINT"
        const val AWAY_POINT_DETAIL = "AWAY_POINT_DETAiL"
    }
}