package com.andrew.associate.hellokotlin.presenter

import android.util.Log
import com.andrew.associate.hellokotlin.model.intface.NextMatchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NextGamePresenter(private val mView: NextMatchView.View,
                        private val gameEventPresenter: GameEventPresenter)
    : NextMatchView.Presenter {

    private val cD = CompositeDisposable()

    override fun getGameNextItem() {
        mView.showProgress()
        cD.add(gameEventPresenter.getEventNextLeague("4332")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).doOnError{ error -> Log.d("MainClass", "Something Went Wrong")}
            .subscribe ({
                mView.displayGame(it.events)
                mView.hideProgress()
            },{ throwable -> Log.d("MainClass", "Something Went Wrong") }))
    }
}