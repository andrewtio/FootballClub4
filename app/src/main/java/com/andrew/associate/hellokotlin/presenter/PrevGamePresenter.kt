package com.andrew.associate.hellokotlin.presenter

import android.util.Log
import com.andrew.associate.hellokotlin.model.intface.PrevMatchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PrevGamePresenter(private val mView : PrevMatchView.View,
                        private val matchEventPresenter: GameEventPresenter) :
    PrevMatchView.Presenter {

    private val cD = CompositeDisposable()

    override fun getGamePrevItem() {
        mView.showProgress()
        cD.add(matchEventPresenter.getEventPastLeague("4332")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).doOnError{ error -> Log.d("MainClass", "Something Went Wrong")}
            .subscribe ({
                mView.displayGame(it.events)
                mView.hideProgress()
            }, { throwable -> Log.d("MainClass", "Something Went Wrong") }))
    }
}