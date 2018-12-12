package com.andrew.associate.hellokotlin.presenter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.model.ClubItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.club_list_game.view.*

class ClubAdapter (private val ctx: Context,
                   private val clubItem: List<ClubItem>,
                   private val listener: (ClubItem) -> Unit)
    : RecyclerView.Adapter<ClubViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        ClubViewHolder(LayoutInflater.from(ctx).inflate(R.layout.club_list_game, p0, false))

    override fun getItemCount(): Int = clubItem.size

    override fun onBindViewHolder(p0: ClubViewHolder, p1: Int) {
        p0.bind(clubItem[p1], listener)
    }

}

class ClubViewHolder(v: View): RecyclerView.ViewHolder(v) {
    fun bind(clubItem: ClubItem, listener: (ClubItem) -> Unit){
        itemView.name_club_game.text = clubItem.clubName
        Glide.with(itemView.context).load(clubItem.clubImage).into(itemView.image_club_game)

        itemView.setOnClickListener{
            listener(clubItem)
        }
    }
}