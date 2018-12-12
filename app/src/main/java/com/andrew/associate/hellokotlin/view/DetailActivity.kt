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
    private lateinit var id_event: String
    private var home_team : String? = null
    private var away_team : String? = null
    private var home_score : String? = null
    private var away_score : String? = null
    private var date_event : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent

        id_event    = intent.getStringExtra("id_event")
        home_team   = intent.getStringExtra("home_team")
        home_score  = intent.getStringExtra("home_score")
        away_team   = intent.getStringExtra("away_team" )
        away_score  = intent.getStringExtra("away_score")
        date_event  = intent.getStringExtra("date_event")

        progressBar = progress_bar_detail

        detail_date_tv.text  = date_event?.formatDate()
        fc_home_name.text   = home_team
        home_goal_detail_tv.text  = home_score
        fc_away_name.text   = away_team
        away_goal_detail_tv.text  = away_score

        showLoading(progressBar)

        setFavState()

        val apiRepository = ApiRepository()
        val gson = Gson()

        detPres = GameDetailPresenter(this,apiRepository, gson)

        detPres.getGameDetail(id_event)
        detPres.getClubImage(home_team, "Home")
        detPres.getClubImage(away_team, "Away")

        toast("Match Detail")
        supportActionBar?.title = "Match Detail"

    }

    override fun showDetailGame(item: List<GameDetailDataItems>){

        // HOME
        val home_GoalScorer    = getDataList(item[0].strHomeGoalDetails)
        val home_Shot           = getDataList(item[0].intHomeShots)
        val home_GK     = getDataList(item[0].strHomeLineupGoalkeeper)
        val home_Def        = getDataList(item[0].strHomeLineupDefense)
        val home_MF       = getDataList(item[0].strHomeLineupMidfield)
        val home_FW        = getDataList(item[0].strHomeLineupForward)
        val home_Sub    = getDataList(item[0].strHomeLineupSubstitutes)

        setToTextDetails(home_GoalScorer, home_scorer_tv)
        setToTextDetails(home_Shot, home_shots_tv)
        setToTextDetails(home_GK, home_gk_tv)
        setToTextDetails(home_Def, home_def_tv)
        setToTextDetails(home_MF, home_mid_tv)
        setToTextDetails(home_FW, home_fw_tv)
        setToTextDetails(home_Sub, home_sub_tv)

        // AWAY
        val away_GoalScorer    = getDataList(item[0].strAwayGoalDetails)
        val away_Shot           = getDataList(item[0].intAwayShots)
        val away_GK     = getDataList(item[0].strAwayLineupGoalkeeper)
        val away_Def        = getDataList(item[0].strAwayLineupDefense)
        val away_MF       = getDataList(item[0].strAwayLineupMidfield)
        val away_FW        = getDataList(item[0].strAwayLineupForward)
        val away_Sub    = getDataList(item[0].strAwayLineupSubstitutes)

        setToTextDetails(away_GoalScorer, away_scorer_tv)
        setToTextDetails(away_Shot, away_shots_tv)
        setToTextDetails(away_GK, away_gk_tv)
        setToTextDetails(away_Def, away_def_tv)
        setToTextDetails(away_MF, away_mid_tv)
        setToTextDetails(away_FW, away_fw_tv)
        setToTextDetails(away_Sub, away_sub_tv)

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
                    Favorite.GAME_ID to id_event,
                    Favorite.DATE_GAME to date_event,
                    Favorite.HOME_CLUB to home_team,
                    Favorite.AWAY_CLUB to away_team,
                    Favorite.HOME_POINT to home_score,
                    Favorite.AWAY_POINT to away_score)
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
                    "id_event" to id_event)
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
                "id_event" to id_event)
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