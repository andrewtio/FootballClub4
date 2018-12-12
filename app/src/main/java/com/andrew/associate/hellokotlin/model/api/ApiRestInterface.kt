package com.andrew.associate.hellokotlin.model.api

import android.net.Uri
import com.andrew.associate.hellokotlin.BuildConfig
import com.andrew.associate.hellokotlin.model.response.GameResponse
import com.andrew.associate.hellokotlin.model.response.TeamResponses
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

object ApiRestInterface {

    fun getEventsPastLeague(league: String? = "4335"): String {
        return Uri.parse(BuildConfig.URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.API)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id",league)
            .build()
            .toString()
    }

    fun getEventsNextLeague(league: String? = "4335"): String {
        return Uri.parse(BuildConfig.URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.API)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id",league)
            .build().toString()
    }

    fun getLookupEvent(event: String? = ""): String {
        return Uri.parse(BuildConfig.URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.API)
            .appendPath("lookupevent.php")
            .appendQueryParameter("id",event)
            .build().toString()
    }

    fun getImageClub(badgeTeam: String? = ""): String {
        return Uri.parse(BuildConfig.URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.API)
            .appendPath("searchteams.php")
            .appendQueryParameter("t", badgeTeam)
            .build().toString()
    }
}