package com.andrew.associate.hellokotlin.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.model.GameItems
import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.model.intface.FavGameView
import com.andrew.associate.hellokotlin.model.invisible
import com.andrew.associate.hellokotlin.model.repository.RepoPresenter
import com.andrew.associate.hellokotlin.model.visible
import com.andrew.associate.hellokotlin.presenter.FavGamePresenter
import com.andrew.associate.hellokotlin.presenter.GameEventPresenter
import com.andrew.associate.hellokotlin.presenter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_fav_game.*

class FavGameFragment: Fragment(), FavGameView.View {

    private lateinit var fGP: FavGamePresenter
    private var mL: MutableList<GameItems> = mutableListOf()

    override fun showFavGame(gameList: List<GameItems>) {
        mL.clear()
        mL.addAll(gameList)
        val lM = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_game_fav.layoutManager = lM
        rv_game_fav.adapter = RecyclerViewAdapter(gameList, context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        val treatment= ApiRepository.getAPI().create(ApiRestInterface::class.java)
        val demand = GameEventPresenter(treatment)
        val repo = RepoPresenter(context!!)

        fGP = FavGamePresenter(demand, this, repo)
        fGP.getGameItem()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fav_game, container, false)
    }

    override fun showProgress() {
        ProgressGameFav.visible()
        rv_game_fav.visibility=View.VISIBLE
    }

    override fun hideProgress() {
        ProgressGameFav.invisible()
        rv_game_fav.visibility=View.INVISIBLE
    }

}