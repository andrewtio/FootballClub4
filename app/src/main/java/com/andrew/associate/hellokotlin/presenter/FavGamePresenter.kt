package com.andrew.associate.hellokotlin.presenter

import android.util.Log
import com.andrew.associate.hellokotlin.model.GameItems
import com.andrew.associate.hellokotlin.model.intface.FavGameView
import com.andrew.associate.hellokotlin.model.support.SupportPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavGamePresenter (private val fMV: FavGameView.View,
                        private val gEP: GameEventPresenter,
                        private val rP: SupportPresenter): FavGameView.Presenter{

    override fun getGameItem(){
        val cD = CompositeDisposable()
        fMV.showProgress()
        val favList = rP.getGameDb()

        val gameList: MutableList<GameItems> = mutableListOf()
        for (f in favList){
            cD.add(gEP.getLookUpEvent(f.gameId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError{ error -> Log.d("MainClass", "Something Went Wrong")}
                .subscribe({
                    gameList.add(it.events[0])
                    fMV.showFavGame(gameList)
                    fMV.hideProgress()
                    fMV.stealthSwipe()
                }, { throwable -> Log.d("MainClass", "Something Went Wrong") }))
        }


        if (favList.isEmpty()){
            fMV.hideProgress()
            fMV.showFavGame(gameList)
        }
    }
}