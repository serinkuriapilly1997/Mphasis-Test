package com.app.mymainapp.remoteservice

import com.app.mymainapp.models.PeopleResponseItem
import com.app.mymainapp.models.RoomsListResponse
import com.app.mymainapp.models.TestApiResponseModel
import retrofit2.Response

interface ApiHelper {

    suspend fun getPosts(): Response<List<TestApiResponseModel>>
    suspend fun getPeople(): Response<List<PeopleResponseItem>>
    suspend fun getRooms(): Response<List<RoomsListResponse>>
}