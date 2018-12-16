package com.andrew.associate.hellokotlin.model.api

import com.andrew.associate.hellokotlin.BuildConfig

object ApiRestInterface {

    fun getEventsPastLeague(league: String? = "4328"): String {
        return BuildConfig.URL + "api/v1/json/${BuildConfig.API}" + "/eventspastleague.php?id=" + league

//        return Uri.parse(BuildConfig.URL).buildUpon()
//            .appendPath("api")
//            .appendPath("v1")
//            .appendPath("json")
//            .appendPath(BuildConfig.API)
//            .appendPath("eventspastleague.php")
//            .appendQueryParameter("id",league)
//            .build()
//            .toString()
    }

    fun getEventsNextLeague(league: String? = "4328"): String {
        return BuildConfig.URL + "api/v1/json/${BuildConfig.API}" + "/eventsnextleague.php?id=" + league


//        return Uri.parse(BuildConfig.URL).buildUpon()
//            .appendPath("api")
//            .appendPath("v1")
//            .appendPath("json")
//            .appendPath(BuildConfig.API)
//            .appendPath("eventsnextleague.php")
//            .appendQueryParameter("id",league)
//            .build().toString()
    }

    fun getLookupEvent(event: String? = ""): String {
        return BuildConfig.URL + "api/v1/json/${BuildConfig.API}" + "/lookupevent.php?id=" + event


//        return Uri.parse(BuildConfig.URL).buildUpon()
//            .appendPath("api")
//            .appendPath("v1")
//            .appendPath("json")
//            .appendPath(BuildConfig.API)
//            .appendPath("lookupevent.php")
//            .appendQueryParameter("id",event)
//            .build().toString()
    }

    fun getImageClub(badgeTeam: String? = ""): String {
        return BuildConfig.URL + "api/v1/json/${BuildConfig.API}" + "/searchteams.php?t=" + badgeTeam

//        return Uri.parse(BuildConfig.URL).buildUpon()
//            .appendPath("api")
//            .appendPath("v1")
//            .appendPath("json")
//            .appendPath(BuildConfig.API)
//            .appendPath("searchteams.php")
//            .appendQueryParameter("t", badgeTeam)
//            .build().toString()
    }
}