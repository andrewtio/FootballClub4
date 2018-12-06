package com.andrew.associate.hellokotlin.presenter

import com.andrew.associate.hellokotlin.model.GameItems
import com.andrew.associate.hellokotlin.model.intface.FavGameView
import com.andrew.associate.hellokotlin.model.repository.RepoPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavGamePresenter (private val gEP: GameEventPresenter,
                        private val fMV: FavGameView.View,
                        private val rP: RepoPresenter): FavGameView.Presenter{

    override fun getGameItem(){
        val cD = CompositeDisposable()
        fMV.showProgress()
        val favList = rP.getGameDb()

        val gameList: MutableList<GameItems> = mutableListOf()
        for (f in favList){
            cD.add(gEP.getLookUpEvent(f.gameId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{
                    gameList.add(it.events[0])
                    fMV.showFavGame(gameList)
                    fMV.hideProgress()
                })
        }

        if (favList.isEmpty()){
            fMV.hideProgress()
            fMV.showFavGame(gameList)
        }
    }
}