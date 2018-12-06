package com.andrew.associate.hellokotlin.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null,1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Membuat tabel disini
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.GAME_ID to TEXT + UNIQUE,
            Favorite.HOME_CLUB_ID to TEXT,
            Favorite.AWAY_CLUB_ID to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Seperti biasa, lo bisa upgrade tabel disini
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Akses Property untuk Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)