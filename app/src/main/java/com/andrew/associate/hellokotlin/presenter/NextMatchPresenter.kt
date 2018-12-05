package com.andrew.associate.hellokotlin.presenter

import com.andrew.associate.hellokotlin.model.NextMatchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NextMatchPresenter(private val mView: NextMatchView.View, private val matchEventPresenter: GameEventPresenter) :
    NextMatchView.Presenter {

    private val cD = CompositeDisposable()

    override fun getGameNextItem() {
        mView.showProgress()
        cD.add(matchEventPresenter.getEventNextLeague("4332")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                mView.displayGame(it.events)
                mView.hideProgress()

            })
    }
}