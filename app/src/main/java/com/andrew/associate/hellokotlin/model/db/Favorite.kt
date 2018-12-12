package com.andrew.associate.hellokotlin.model.db

class Favorite (val id: Long?,
                val gameId: String?,
                val dateGame: String?,
                val homeClub: String?,
                val awayClub: String?,
                val homePoint: String?,
                val awayPoint: String?,
                val homePointDetail: String?,
                val awayPointDetail: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val GAME_ID = "GAME_ID"
        const val DATE_GAME = "DATE_GAME"
        const val HOME_CLUB = "HOME_CLUB"
        const val HOME_POINT = "HOME_POINT"
        const val HOME_POINT_DETAIL = "HOME_POINT_DETAIL"
        const val AWAY_CLUB = "AWAY_CLUB"
        const val AWAY_POINT = "AWAY_POINT"
        const val AWAY_POINT_DETAIL = "AWAY_POINT_DETAIL"
    }
}