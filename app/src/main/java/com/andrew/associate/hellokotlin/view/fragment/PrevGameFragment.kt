package com.andrew.associate.hellokotlin.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.model.GameDataItems
import com.andrew.associate.hellokotlin.model.intface.GameEventView
import com.andrew.associate.hellokotlin.presenter.GameEventPresenter
import com.andrew.associate.hellokotlin.presenter.PrevGameAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_prev_game.view.*
import org.jetbrains.anko.support.v4.onRefresh
import java.lang.RuntimeException

class PrevGameFragment : Fragment(), GameEventView
{
    private var gDI: MutableList<GameDataItems> = mutableListOf()

    private var listener: OnFragLinkListener? = null

    private lateinit var gEP: GameEventPresenter
    private lateinit var prevAdapter : PrevGameAdapter
    private lateinit var swipeRefPrev : SwipeRefreshLayout
    private lateinit var progBar : ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_prev_game, container,false)

        val rV = v.findViewById<RecyclerView>(R.id.rv_game_prev)

        rV.layoutManager = LinearLayoutManager(context)
        prevAdapter = PrevGameAdapter(gDI, listener)
        rV.adapter = prevAdapter

        swipeRefPrev = v.swipe_refresh_prev
        progBar = v.ProgressGamePrev

        swipeRefPrev.onRefresh{
            gEP.getGamePrevData("4328")
        }

        showLoading()

        val apiRepository = ApiRepository()
        val gson = Gson()
        gEP = GameEventPresenter(this, apiRepository,gson)
        gEP.getGamePrevData("4328")

        return v
    }

    override fun hideLoading() {
        progBar.visibility = View.GONE
    }

    override fun showLoading() {
        progBar.visibility = View.VISIBLE
    }

    override fun showItemGameList(item: List<GameDataItems>) {

        swipeRefPrev.isRefreshing = false
        gDI.clear()
        gDI.addAll(item)
        prevAdapter.notifyDataSetChanged()
        hideLoading()
    }

    interface OnFragLinkListener{
        fun onFragmentLink(game: GameDataItems)
    }

    companion object {
        @JvmStatic
        fun freshInstance() = PrevGameFragment()
    }

    override fun onAttach(ctx: Context){
        super.onAttach(ctx)
        if (ctx is OnFragLinkListener){
            listener = ctx
        }else{
            throw RuntimeException(ctx.toString() + "")
        }
    }

    override fun onDetach(){
        super.onDetach()
        listener = null
    }
}