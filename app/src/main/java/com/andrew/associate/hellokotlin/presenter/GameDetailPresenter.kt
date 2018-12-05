package com.andrew.associate.hellokotlin.presenter

import com.andrew.associate.hellokotlin.model.DetailGameView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GameDetailPresenter (val gView : DetailGameView.View, val gameEP: GameEventPresenter) : DetailGameView.Presenter{

    val cD = CompositeDisposable()

    override fun getClubLogoHome(id: String)
    {
        cD.add(gameEP.getLookUpTeam(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe{
                gView.showClubLogoHome(it.teams[0])
            })
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
}