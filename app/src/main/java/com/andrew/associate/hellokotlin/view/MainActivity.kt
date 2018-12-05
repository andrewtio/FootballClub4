package com.andrew.associate.hellokotlin.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.view.fragment.NextMatchFragment
import com.andrew.associate.hellokotlin.view.fragment.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.prev_match -> {
                    toast("Previous Match")
                    getPrevGame(savedInstanceState)
                }
                R.id.next_match -> {
                    toast("Next Match")
                    getNextGame(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.prev_match

    }

    private fun getPrevGame(savedInstanceState: Bundle?){
        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    PrevMatchFragment(), PrevMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun getNextGame(savedInstanceState: Bundle?){
        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }



}
