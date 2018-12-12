package com.andrew.associate.hellokotlin.presenter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.andrew.associate.hellokotlin.model.GameDetailDataItems
import com.andrew.associate.hellokotlin.model.formatDate
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.model.db.Favorite
import com.andrew.associate.hellokotlin.view.DetailActivity
import kotlinx.android.synthetic.main.item_list_match.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class FavRecyclerViewAdapter(private val fav: List<Favorite>,
                             private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavGameViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavGameViewHolder {
        val v = LayoutInflater.from(p0.context)
            .inflate(R.layout.item_list_match, p0, false)
        return FavGameViewHolder(v)
    }

    override fun getItemCount(): Int = fav.size

    override fun onBindViewHolder(p1: FavGameViewHolder, p0: Int) {
        p1.bind(fav[p0], listener)
    }


}

class FavGameViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    val dateEvent  : TextView = v.find(R.id.game_date_tv)
    val strHomeTeam   : TextView = v.find(R.id.home_club_tv)
    val strAwayTeam   : TextView = v.find(R.id.away_club_tv)
    val intHomeScore  : TextView = v.find(R.id.home_point_tv)
    val intAwayScore  : TextView = v.find(R.id.away_point_tv)

    fun bind(favItem: Favorite, listener: (Favorite) -> Unit ) {

        dateEvent.text = favItem.dateGame?.formatDate()
        strHomeTeam.text = favItem.homeClub
        strAwayTeam.text = favItem.awayClub
        intHomeScore.text = favItem.homePoint
        intAwayScore.text = favItem.awayPoint

        itemView.onClick { listener(favItem) }
    }
}