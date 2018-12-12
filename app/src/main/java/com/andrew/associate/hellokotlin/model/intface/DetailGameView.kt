package com.andrew.associate.hellokotlin.model.intface

import android.widget.ProgressBar
import com.andrew.associate.hellokotlin.model.GameDetailDataItems
import com.andrew.associate.hellokotlin.model.ImageDataItem

interface DetailGameView {

    fun showLoading(progBar: ProgressBar)
    fun hideLoading(progBar: ProgressBar)
    fun showDetailGame (item: List<GameDetailDataItems>)
    fun showHomeClubImage (item: List<ImageDataItem>)
    fun showAwayClubImage (item: List<ImageDataItem>)

}