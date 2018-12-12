package com.andrew.associate.hellokotlin.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.model.db.Favorite
import com.andrew.associate.hellokotlin.model.db.database
import com.andrew.associate.hellokotlin.model.invisible
import com.andrew.associate.hellokotlin.model.visible
import com.andrew.associate.hellokotlin.presenter.FavRecyclerViewAdapter
import com.andrew.associate.hellokotlin.view.DetailActivity
import kotlinx.android.synthetic.main.fragment_fav_game.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class FavGameFragment: Fragment(){

    private var favorite : MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavRecyclerViewAdapter
    private lateinit var favList: RecyclerView

    private fun showFavGame() {
        context?.database?.use{
            swipe_fav.isRefreshing = false
            val outcome = select(Favorite.TABLE_FAVORITE)
            val fav = outcome.parseList(classParser<Favorite>())
            favorite.addAll(fav)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)

        adapter = FavRecyclerViewAdapter(favorite){
            ctx.startActivity<DetailActivity>("id_event" to "${it.gameId}",
                                                    "home_team" to "${it.homeClub}",
                                                    "home_score" to "${it.homePoint}",
                                                    "home_score" to "${it.homePoint}",
                                                    "away_team" to "${it.awayClub}",
                                                    "away_score" to "${it.awayPoint}",
                                                    "away_score" to "${it.awayPoint}",
                                                    "date_event" to "${it.dateGame}")
        }

        favList = rv_game_fav
        favList.layoutManager = LinearLayoutManager(ctx)

        favList.adapter = adapter
        showFavGame()

        swipe_fav.onRefresh{
            favorite.clear()
            showFavGame()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fav_game, container, false)
    }

    fun showProgress() {
        ProgressGameFav.visible()
        rv_game_fav.visibility=View.VISIBLE
    }

    fun hideProgress() {
        ProgressGameFav.invisible()
        rv_game_fav.visibility=View.INVISIBLE
    }

    fun stealthSwipe(){
        swipe_fav.isRefreshing = false
        ProgressGameFav.invisible()
        rv_game_fav.visibility = View.VISIBLE
    }
}