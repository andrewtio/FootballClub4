package com.andrew.associate.hellokotlin.model.intface

import com.andrew.associate.hellokotlin.model.GameDataItems

interface GameEventView{
    fun showLoading()
    fun hideLoading()
    fun showItemGameList(item: List<GameDataItems>)
}