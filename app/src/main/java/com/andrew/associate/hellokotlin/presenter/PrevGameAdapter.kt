package com.andrew.associate.hellokotlin.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.model.GameDataItems
import com.andrew.associate.hellokotlin.view.fragment.PrevGameFragment
import org.jetbrains.anko.find

class PrevGameAdapter ( private val gDI: MutableList<GameDataItems>,
                        private val listener: PrevGameFragment.OnFragLinkListener?)
    : RecyclerView.Adapter<PrevGameAdapter.PrevGameViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PrevGameAdapter.PrevGameViewHolder {
        val v= LayoutInflater.from(p0.context)
            .inflate(R.layout.item_list_match, p0, false)
        return PrevGameViewHolder(v)
    }

    override fun getItemCount(): Int {
        return gDI.size
    }

    override fun onBindViewHolder(p0: PrevGameAdapter.PrevGameViewHolder, p1: Int) {
        p0.bind(gDI[p1], listener)
    }

    class PrevGameViewHolder(v: View): RecyclerView.ViewHolder(v){

        val dateEvent  : TextView = v.find(R.id.game_date_tv)
        val strHomeTeam   : TextView = v.find(R.id.home_club_tv)
        val strAwayTeam   : TextView = v.find(R.id.away_club_tv)
        val intHomeScore  : TextView = v.find(R.id.home_point_tv)
        val intAwayScore  : TextView = v.find(R.id.away_point_tv)

        fun bind(game: GameDataItems, listener: PrevGameFragment.OnFragLinkListener?) {
            dateEvent.text = game.dateEvent
            strHomeTeam.text = game.strHomeTeam
            strAwayTeam.text = game.strAwayTeam
            intHomeScore.text = game.intHomeScore
            intAwayScore.text = game.intAwayScore

            itemView.setOnClickListener {
                listener?.onFragmentLink(game)
            }
        }
    }
}