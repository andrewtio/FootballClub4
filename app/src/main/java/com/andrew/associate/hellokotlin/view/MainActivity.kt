package com.andrew.associate.hellokotlin.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.view.fragment.FavGameFragment
import com.andrew.associate.hellokotlin.view.fragment.NextGameFragment
import com.andrew.associate.hellokotlin.view.fragment.PrevGameFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressDialog = indeterminateProgressDialog("Getting Data! Please wait...")

        progressDialog.show()

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.prev_match -> {
                    toast("Previous Match")
                    getPrevGame(savedInstanceState)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.next_match -> {
                    toast("Next Match")
                    getNextGame(savedInstanceState)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favorite_match -> {
                    toast("Your Favorite Match")
                    getFavGame(savedInstanceState)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
        bottom_navigation.selectedItemId = R.id.prev_match

        progressDialog.dismiss()
    }

    private fun getPrevGame(savedInstanceState: Bundle?){
        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    PrevGameFragment(), PrevGameFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun getNextGame(savedInstanceState: Bundle?){
        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    NextGameFragment(), NextGameFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun getFavGame(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    FavGameFragment(), FavGameFragment::class.java.simpleName)
                .commit()
        }
    }
}
