package com.andrew.associate.hellokotlin.model.intface

import com.andrew.associate.hellokotlin.model.GameDataItems
import com.andrew.associate.hellokotlin.model.GameDetailDataItems

interface PrevMatchView {

    fun hideLoading()
    fun showLoading()
    fun showDataGameItem(item: List<GameDataItems>)

}