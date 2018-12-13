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
import com.andrew.associate.hellokotlin.presenter.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_game.*
import kotlinx.android.synthetic.main.fragment_next_game.view.*
import kotlinx.android.synthetic.main.fragment_prev_game.*
import kotlinx.android.synthetic.main.fragment_prev_game.view.*
import org.jetbrains.anko.support.v4.onRefresh
import java.lang.RuntimeException

class NextGameFragment : Fragment(), GameEventView
{
    private var gDI: MutableList<GameDataItems> = mutableListOf()

    private var listener: OnFragLinkListener? = null

    private lateinit var gEP: GameEventPresenter
    private lateinit var nextAdapter : NextGameAdapter
    private lateinit var swipeRefNext : SwipeRefreshLayout
    private lateinit var progBar : ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_next_game, container,false)

        val rV = v.findViewById<RecyclerView>(R.id.rv_game_next)

        rV.layoutManager = LinearLayoutManager(context)
        nextAdapter = NextGameAdapter(gDI, listener)
        rV.adapter = nextAdapter

        swipeRefNext = v.swipe_refresh_next
        progBar = v.ProgressGameNext

        swipeRefNext.onRefresh{
            gEP.getGameNextData("4328")
        }

        showLoading()

        val apiRepository = ApiRepository()
        val gson = Gson()
        gEP = GameEventPresenter(this, apiRepository,gson)
        gEP.getGameNextData("4328")

        return v
    }

    override fun hideLoading() {
        progBar.visibility = View.GONE
    }

    override fun showLoading() {
        progBar.visibility = View.VISIBLE
    }

    override fun showItemGameList(item: List<GameDataItems>) {

        swipeRefNext.isRefreshing = false
        gDI.clear()
        gDI.addAll(item)
        nextAdapter.notifyDataSetChanged()
        hideLoading()
    }

    interface OnFragLinkListener{
        fun onFragmentLink(game: GameDataItems)
    }

    companion object {
        @JvmStatic
        fun freshInstance() = NextGameFragment()
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
