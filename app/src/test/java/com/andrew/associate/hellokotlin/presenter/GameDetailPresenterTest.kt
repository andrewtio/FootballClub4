package com.andrew.associate.hellokotlin.presenter

import com.andrew.associate.hellokotlin.model.GameDetailDataItems
import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.model.intface.DetailGameView
import com.andrew.associate.hellokotlin.model.response.DetailGameResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GameDetailPresenterTest{

    @Mock
    private
    lateinit var dGV: DetailGameView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: GameDetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = GameDetailPresenter(dGV,apiRepository,gson)
    }

    @Test
    fun testGetGameDetail(){
        val gameData: MutableList<GameDetailDataItems> = mutableListOf()
        val response = DetailGameResponse(gameData)
        val game = "4328"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(ApiRestInterface.getLookupEvent(game)).await()
            , DetailGameResponse::class.java
            )).thenReturn(response)

            presenter.getGameDetail(game)

            Mockito.verify(dGV).showDetailGame(gameData)
        }
    }
}