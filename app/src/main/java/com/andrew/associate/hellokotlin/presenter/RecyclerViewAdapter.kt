package com.andrew.associate.hellokotlin.presenter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrew.associate.hellokotlin.model.GameItems
import com.andrew.associate.hellokotlin.model.formatDate
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.view.DetailActivity
import kotlinx.android.synthetic.main.item_list_match.view.*
import org.jetbrains.anko.startActivity

class RecyclerViewAdapter(private val lI: List<GameItems>, val ctx: Context?) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_list_match, p0, false))
        }

    override fun getItemCount(): Int = lI.size

    override fun onBindViewHolder(p1: ViewHolder, p0: Int) {
        p1.bindItem(lI[p0])
    }

    inner class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bindItem(mEvent: GameItems) {

//            itemView.name_team_view.text = mEvent.clubName
//            Glide.with(itemView.context).load(mEvent.clubLogo).into(itemView.image_team_view)

            itemView.game_date_tv.text = mEvent.dateEvent?.formatDate()
            itemView.home_club_tv.text = mEvent.strHomeTeam
            itemView.home_point_tv.text = mEvent.intHomeScore
            itemView.away_club_tv.text = mEvent.strAwayTeam
            itemView.away_point_tv.text = mEvent.intAwayScore

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailActivity>("match" to mEvent)
            }
        }
    }
}