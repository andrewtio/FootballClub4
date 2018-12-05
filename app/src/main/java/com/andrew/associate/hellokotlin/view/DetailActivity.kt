package com.andrew.associate.hellokotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.andrew.associate.hellokotlin.model.*
import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.presenter.GameDetailPresenter
import com.andrew.associate.hellokotlin.presenter.GameEventPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailGameView.View {

    private lateinit var detPres: GameDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val treatment = ApiRepository.getAPI().create(ApiRestInterface::class.java)
        val demand = GameEventPresenter(treatment)
        detPres = GameDetailPresenter(this,demand)

        val game = intent.getParcelableExtra<GameItems>("match")
        detPres.getClubLogoAway(game.idAwayTeam)
        detPres.getClubLogoHome(game.idHomeTeam)

        toast("Match Detail")
        getGameItems(game)

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

}