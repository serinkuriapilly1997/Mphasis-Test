package com.app.mymainapp.remoteservice

import com.app.mymainapp.models.PeopleResponseItem
import com.app.mymainapp.models.RoomsListResponse
import com.app.mymainapp.models.TestApiResponseModel
import com.app.mymainapp.utils.Constants.Companion.PEOPLE
import com.app.mymainapp.utils.Constants.Companion.ROOMS
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<TestApiResponseModel>>

    @GET(PEOPLE)
    suspend fun getPeople(): Response<List<PeopleResponseItem>>

    @GET(ROOMS)
    suspend fun getRooms(): Response<List<RoomsListResponse>>
}