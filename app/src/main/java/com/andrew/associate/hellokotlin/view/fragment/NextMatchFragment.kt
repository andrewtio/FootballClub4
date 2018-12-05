package com.andrew.associate.hellokotlin.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrew.associate.hellokotlin.model.GameItems
import com.andrew.associate.hellokotlin.model.NextMatchView
import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.model.invisible
import com.andrew.associate.hellokotlin.model.visible
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.presenter.GameEventPresenter
import com.andrew.associate.hellokotlin.presenter.NextMatchPresenter
import com.andrew.associate.hellokotlin.presenter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_next_match.*

class NextMatchFragment : Fragment(), NextMatchView.View {

    private var mL : MutableList<GameItems> = mutableListOf()

    private lateinit var nMP : NextMatchPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = ApiRepository.getAPI().create(ApiRestInterface::class.java)
        val request = GameEventPresenter(service)


        nMP = NextMatchPresenter(this, request)
        nMP.getGameNextItem()

    }


    override fun hideProgress() {
        ProgressGameNext.invisible()
        rv_game_next.visibility = View.VISIBLE
    }

    override fun showProgress() {
        ProgressGameNext.visible()
        rv_game_next.visibility = View.INVISIBLE

    }
    override fun displayGame(gameItem: List<GameItems>) {
        mL.clear()
        mL.addAll(gameItem)
        val lM = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_game_next.layoutManager = lM
        rv_game_next.adapter = RecyclerViewAdapter(gameItem,context)
    }

}

