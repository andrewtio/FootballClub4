package com.andrew.associate.hellokotlin.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.model.GameDataItems
import com.andrew.associate.hellokotlin.view.fragment.FavGameFragment
import com.andrew.associate.hellokotlin.view.fragment.NextGameFragment
import com.andrew.associate.hellokotlin.view.fragment.PrevGameFragment
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity(),
        PrevGameFragment.OnFragLinkListener,
        NextGameFragment.OnFragLinkListener{

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Stetho.initializeWithDefaults(this)

        revealGameFragment( PrevGameFragment.freshInstance() )

        bottom_navigation_home.setOnNavigationItemSelectedListener(BottomNavigationItemSelectedListener)

    }

    override fun onFragmentLink(game: GameDataItems) {
        startActivity<DetailActivity>(
            "id_event" to game.IdEvent,
            "date_event" to game.dateEvent,
            "home_team" to game.strHomeTeam,
            "home_score" to game.intHomeScore,
            "away_team" to game.strAwayTeam,
            "away_score" to game.intAwayScore
        )
    }

    private val BottomNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { game ->
        when (game.itemId) {
            R.id.prev_match -> {
                toast("Previous Match")
                val frag = PrevGameFragment.freshInstance()
                revealGameFragment(frag)
                return@OnNavigationItemSelectedListener true

            }
            R.id.next_match -> {
                toast("Next Match")
                val frag = NextGameFragment.freshInstance()
                revealGameFragment(frag)
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite_match -> {
                toast("Your Favorite Match")
                val frag = FavGameFragment()
                revealGameFragment(frag)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun revealGameFragment(fragment: Fragment){
        val traffic = supportFragmentManager.beginTransaction()
        traffic.replace(R.id.main_activity_fragment_container, fragment)
        traffic.addToBackStack(null)
        traffic.commit()
    }
}