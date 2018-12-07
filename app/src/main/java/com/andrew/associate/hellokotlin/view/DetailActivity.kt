package com.andrew.associate.hellokotlin.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.andrew.associate.hellokotlin.model.*
import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.R.menu.detail_menu
import com.andrew.associate.hellokotlin.model.db.Favorite
import com.andrew.associate.hellokotlin.model.intface.DetailGameView
import com.andrew.associate.hellokotlin.model.repository.RepoPresenter
import com.andrew.associate.hellokotlin.presenter.GameDetailPresenter
import com.andrew.associate.hellokotlin.presenter.GameEventPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailGameView.View {

    private lateinit var detPres: GameDetailPresenter

    private var menuItem: Menu? = null
    private var isFav: Boolean = false
    private lateinit var gI: GameItems

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val treatment = ApiRepository.getAPI().create(ApiRestInterface::class.java)
        val demand = GameEventPresenter(treatment)
        val repo = RepoPresenter(applicationContext)

        detPres = GameDetailPresenter(this,demand, repo)

        gI = intent.getParcelableExtra("match")
        detPres.favState(gI.idEvent.toString())
        detPres.getClubLogoAway(gI.idAwayTeam)
        detPres.getClubLogoHome(gI.idHomeTeam)

        toast("Match Detail")
        getGameItems(gI)

        supportActionBar?.title = "Match Detail"

    }

    private fun getGameItems(gameItems: GameItems){

        detail_date_tv.text = gameItems.dateEvent?.formatDate()
        home_goal_detail_tv.text = gameItems.intHomeScore
        away_goal_detail_tv.text = gameItems.intAwayScore
        home_scorer_tv.text = gameItems.strHomeGoalDetails
        away_scorer_tv.text = gameItems.strAwayGoalDetails
        fc_away_name.text = gameItems.strAwayTeam
        fc_home_name.text = gameItems.strHomeTeam
        away_gk_tv.text = gameItems.strAwayLineupGoalkeeper
        home_gk_tv.text = gameItems.strHomeLineupGoalkeeper
        away_shots_tv.text = gameItems.intAwayShots
        home_shots_tv.text = gameItems.intHomeShots
        away_def_tv.text = gameItems.strHomeLineupDefense
        home_def_tv.text = gameItems.strAwayLineupDefense
        away_mid_tv.text = gameItems.strAwayLineupMidfield
        home_mid_tv.text = gameItems.strHomeLineupMidfield
        home_fw_tv.text = gameItems.strHomeLineupForward
        away_fw_tv.text = gameItems.strAwayLineupForward
        away_sub_tv.text = gameItems.strAwayLineupSubstitutes
        home_sub_tv.text = gameItems.strHomeLineupSubstitutes

    }

    override fun showClubLogoHome(team: Teams){
        Picasso.get()
            .load(team.strTeamBadge)
            .into(fc_home_logo)
    }

    override fun showClubLogoAway(team: Teams){
        Picasso.get()
            .load(team.strTeamBadge)
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
                if (!isFav){
                    detPres.addToFav(
                        gI.idEvent.toString(), gI.idHomeTeam, gI.idAwayTeam)
                toast("Added to Favorite")
                isFav = !isFav
            }else{
                detPres.removeFromFav(gI.idEvent.toString())
                toast("Remove from Favorite")
                isFav = !isFav
            }
            setFav()
            true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFav(){
        if (isFav)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
    }

    override fun setFavState(favList: List<Favorite>) {
        if(!favList.isEmpty()) isFav = true
    }
}