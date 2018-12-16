package com.andrew.associate.hellokotlin.presenter

import com.andrew.associate.hellokotlin.model.GameDataItems
import com.andrew.associate.hellokotlin.model.api.ApiRepository
import com.andrew.associate.hellokotlin.model.api.ApiRestInterface
import com.andrew.associate.hellokotlin.model.intface.GameEventView
import com.andrew.associate.hellokotlin.model.response.GameResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GameEventPresenterTest{

    @Mock
    private
    lateinit var gEV: GameEventView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: GameEventPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = GameEventPresenter(gEV,apiRepository,gson)
    }

    @Test
    fun testGetGameNextData(){
        val gameData: MutableList<GameDataItems> = mutableListOf()
        val response = GameResponse(gameData)
        val game = "4328"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(ApiRestInterface.getEventsNextLeague(game)).await()
                    , GameResponse::class.java
                )
            ).thenReturn(response)

            presenter.getGameNextData(game)

            Mockito.verify(gEV).showItemGameList(gameData)
        }
    }

}