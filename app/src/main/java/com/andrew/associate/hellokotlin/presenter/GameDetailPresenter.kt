package com.andrew.associate.hellokotlin.presenter

import android.util.Log
import com.andrew.associate.hellokotlin.model.intface.DetailGameView
import com.andrew.associate.hellokotlin.model.support.SupportPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GameDetailPresenter (val gView : DetailGameView.View,
                           val gameEP: GameEventPresenter,
                           val rP: SupportPresenter) : DetailGameView.Presenter{

    val cD = CompositeDisposable()

    override fun getClubLogoHome(id: String)
    {
        cD.add(gameEP.getLookUpTeam(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError{ error -> Log.d("MainClass", "Something Went Wrong")}
            .subscribe({
                gView.showClubLogoHome(it.teams[0])
            },{ throwable -> Log.d("MainClass", "Something Went Wrong") })
        )
    }

    override fun getClubLogoAway(id:String)
    {
        cD.add(gameEP.getLookUpTeam(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe{
                gView.showClubLogoAway(it.teams[0])
            })
    }

    override fun addToFav (gameId: String, homeClubId: String, awayClubId: String){
        rP.addFavItem(gameId, homeClubId, awayClubId)
    }

    override fun removeFromFav(id: String){
        rP.removeFavItem(id)
    }

    override fun favState(id: String){
        gView.setFavState(rP.scanFav(id))
    }

}