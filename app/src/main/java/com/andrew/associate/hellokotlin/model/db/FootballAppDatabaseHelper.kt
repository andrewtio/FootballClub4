package com.andrew.associate.hellokotlin.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FootballAppDatabaseHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoritesGame.db", null,1) {

    companion object {
        private var instance: FootballAppDatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FootballAppDatabaseHelper {
            if (instance == null) {
                instance = FootballAppDatabaseHelper(ctx.applicationContext)
            }
            return instance as FootballAppDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Membuat tabel disini
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.GAME_ID to TEXT + UNIQUE,
            Favorite.DATE_GAME to TEXT,
            Favorite.HOME_CLUB to TEXT,
            Favorite.AWAY_CLUB to TEXT,
            Favorite.HOME_POINT to TEXT,
            Favorite.AWAY_POINT to TEXT,
            Favorite.HOME_POINT_DETAIL to TEXT,
            Favorite.AWAY_POINT_DETAIL to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Seperti biasa, lo bisa upgrade tabel disini
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Akses Property untuk Context
val Context.database: FootballAppDatabaseHelper
    get() = FootballAppDatabaseHelper.getInstance(applicationContext)