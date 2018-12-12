package com.andrew.associate.hellokotlin.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.andrew.associate.hellokotlin.R
import com.andrew.associate.hellokotlin.R.array.*
import com.andrew.associate.hellokotlin.model.ClubItem
import com.andrew.associate.hellokotlin.model.GameDataItems
import com.andrew.associate.hellokotlin.presenter.ClubAdapter
import com.andrew.associate.hellokotlin.view.fragment.FavGameFragment
import com.andrew.associate.hellokotlin.view.fragment.NextGameFragment
import com.andrew.associate.hellokotlin.view.fragment.PrevGameFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var gDI: MutableList<ClubItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressDialog = indeterminateProgressDialog("Mengambil Data, Mohon Tunggu Sebentar...")

        progressDialog.show()

        getClubsData()

        progressDialog.dismiss()
        rv_main_activity.layoutManager = LinearLayoutManager(this)
        rv_main_activity.adapter = ClubAdapter(this, gDI) { clubClicked(it) }
    }

    private fun clubClicked(clubData: ClubItem){
        startActivity<ClubDetails>(ClubDetails.CLUB_NAME to clubData.clubName,
                                        ClubDetails.CLUB_BADGE to clubData.clubImage,
                                        ClubDetails.CLUB_DES to clubData.clubDescription)
    }

    private fun getClubsData(){
        val clubName = resources.getStringArray(club_name)
        val clubBadge = resources.obtainTypedArray(club_image)
        val clubDes = resources.getStringArray(club_description)

        gDI.clear()

        for (i in clubName.indices){
            gDI.add(ClubItem(clubName[i],
                clubBadge.getResourceId(i,0), clubDes[i]))
        }
        clubBadge.recycle()
    }

}
