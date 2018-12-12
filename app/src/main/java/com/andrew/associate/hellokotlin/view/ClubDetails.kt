package com.andrew.associate.hellokotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class ClubDetails : AppCompatActivity() {

    companion object {
        const val CLUB_NAME              = "name"
        const val CLUB_BADGE             = "badge"
        const val CLUB_DES               = "description"
    }
    private var club_name:           String  = ""
    private var club_badge:          Int     = 0
    private var club_des:    String  = ""

    lateinit var name_tv    : TextView
    lateinit var badge_iv   : ImageView
    lateinit var  des_tv   : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TeamDetailsUI().setContentView(this)

        verticalLayout {
            padding = dip(18)

            badge_iv = imageView()
                .lparams(width = dip(120),
                    height = wrapContent
                ) {
                    gravity = Gravity.CENTER
                }

            name_tv = textView()
                .lparams(width = wrapContent) {
                    gravity = Gravity.CENTER
                    topMargin = dip(15)
                }

            des_tv = textView()
                .lparams(width = wrapContent) {
                    margin = dip(30)
                }
        }

        club_name        = intent.getStringExtra(CLUB_NAME)
        club_badge       = intent.getIntExtra(CLUB_BADGE,0)
        club_des         = intent.getStringExtra(CLUB_DES)

        name_tv.text = club_name
        Glide.with(badge_iv).load(club_badge).into(badge_iv)
        des_tv.text = club_des

    }

}