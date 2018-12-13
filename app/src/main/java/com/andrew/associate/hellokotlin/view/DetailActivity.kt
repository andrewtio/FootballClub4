package com.andrew.associate.hellokotlin.view

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.andrew.associate.hellokotlin.model.*
import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.R.menu.detail_menu
import com.andrew.associate.hellokotlin.model.db.Favorite
import com.andrew.associate.hellokotlin.model.db.database
import com.andrew.associate.hellokotlin.model.intface.DetailGameView
import com.andrew.associate.hellokotlin.presenter.GameDetailPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailGameView {

    private lateinit var detPres: GameDetailPresenter
    private lateinit var progressBar: ProgressBar

    private var menuItem: Menu? = null
    private var isFav: Boolean = false
    private lateinit var idEvent: String
    private var homeTeam : String? = null
    private var awayTeam : String? = null
    private var homeScore : String? = null
    private var awayScore : String? = null
    private var dateEvent : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent

        idEvent    = intent.getStringExtra("id_event")
        homeTeam   = intent.getStringExtra("home_team")
        homeScore  = intent.getStringExtra("home_score")
        awayTeam   = intent.getStringExtra("away_team" )
        awayScore  = intent.getStringExtra("away_score")
        dateEvent  = intent.getStringExtra("date_event")

        progressBar = progress_bar_detail

        detail_date_tv.text  = dateEvent?.formatDate()
        fc_home_name.text   = homeTeam
        home_goal_detail_tv.text  = homeScore
        fc_away_name.text   = awayTeam
        away_goal_detail_tv.text  = awayScore

        showLoading(progressBar)

        setFavState()

        val apiRepository = ApiRepository()
        val gson = Gson()

        detPres = GameDetailPresenter(this,apiRepository, gson)

        detPres.getGameDetail(idEvent)
        detPres.getClubImage(homeTeam, "Home")
        detPres.getClubImage(awayTeam, "Away")

        toast("Match Detail")
        supportActionBar?.title = "Match Detail"

    }

    override fun showDetailGame(item: List<GameDetailDataItems>){

        // HOME
        val homeGoalScorer    = getDataList(item[0].strHomeGoalDetails)
        val homeShot           = getDataList(item[0].intHomeShots)
        val homeGK     = getDataList(item[0].strHomeLineupGoalkeeper)
        val homeDef        = getDataList(item[0].strHomeLineupDefense)
        val homeMF       = getDataList(item[0].strHomeLineupMidfield)
        val homeFW        = getDataList(item[0].strHomeLineupForward)
        val homeSub    = getDataList(item[0].strHomeLineupSubstitutes)

        setToTextDetails(homeGoalScorer, home_scorer_tv)
        setToTextDetails(homeShot, home_shots_tv)
        setToTextDetails(homeGK, home_gk_tv)
        setToTextDetails(homeDef, home_def_tv)
        setToTextDetails(homeMF, home_mid_tv)
        setToTextDetails(homeFW, home_fw_tv)
        setToTextDetails(homeSub, home_sub_tv)

        // AWAY
        val awayGoalScorer    = getDataList(item[0].strAwayGoalDetails)
        val awayShot           = getDataList(item[0].intAwayShots)
        val awayGK     = getDataList(item[0].strAwayLineupGoalkeeper)
        val awayDef        = getDataList(item[0].strAwayLineupDefense)
        val awayMF       = getDataList(item[0].strAwayLineupMidfield)
        val awayFW        = getDataList(item[0].strAwayLineupForward)
        val awaySub    = getDataList(item[0].strAwayLineupSubstitutes)

        setToTextDetails(awayGoalScorer, away_scorer_tv)
        setToTextDetails(awayShot, away_shots_tv)
        setToTextDetails(awayGK, away_gk_tv)
        setToTextDetails(awayDef, away_def_tv)
        setToTextDetails(awayMF, away_mid_tv)
        setToTextDetails(awayFW, away_fw_tv)
        setToTextDetails(awaySub, away_sub_tv)

        hideLoading(progressBar)

    }

    private fun getDataString(text: String?, data: String): String {
        return if (data != "null")
            getString(R.string.textDetails,text, data)
        else
            getString(R.string.textDetails, ""," - ")
    }

    private fun getDataList (item: String?): List<String> {
        return item.toString().split(";")
    }

    private fun setToTextDetails(Items: List<String> , textView: TextView){
        for (value in Items) {
            textView.text = getDataString(textView.text.toString(), value.trim() )
        }
    }

    override fun showHomeClubImage(item: List<ImageDataItem>){
        Picasso.get()
            .load(item[0].mTeamBadge)
            .into(fc_home_logo)
    }

    override fun showAwayClubImage(item: List<ImageDataItem>){
        Picasso.get()
            .load(item[0].mTeamBadge)
            .into(fc_away_logo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFav()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFav)
                    removeFavItem() else addFavItem()
                isFav = !isFav
                setFav()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addFavItem(){

        Log.d("Debug", "AddFavItem")

        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.GAME_ID to idEvent,
                    Favorite.DATE_GAME to dateEvent,
                    Favorite.HOME_CLUB to homeTeam,
                    Favorite.AWAY_CLUB to awayTeam,
                    Favorite.HOME_POINT to homeScore,
                    Favorite.AWAY_POINT to awayScore)
            }
            toast("Added to your Favorite List")
        }catch (e: SQLiteConstraintException){

        }
    }

    private fun removeFavItem(){

        Log.d("Debug", "removeFavItem")

        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(GAME_ID = {id_event})",
                    "id_event" to idEvent)
            }
            toast("Removed from your Favorite List")
        }catch (e:SQLiteConstraintException){

        }
    }

    private fun setFav(){
        if (isFav)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
    }

    private fun setFavState() {
        database.use{
            val outcome = select(Favorite.TABLE_FAVORITE).whereArgs("( GAME_ID = {id_event})",
                "id_event" to idEvent)
            val favGame = outcome.parseList(classParser<Favorite>())
            if (!favGame.isEmpty()) isFav = true
        }
    }


    override fun showLoading(progBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading(progBar: ProgressBar){
        progressBar.visibility = View.GONE
    }
}