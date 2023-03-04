package com.app.mymainapp.remoteservice

import com.app.mymainapp.models.PeopleResponseItem
import com.app.mymainapp.models.RoomsListResponse
import com.app.mymainapp.models.TestApiResponseModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImplementation @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getPosts(): Response<List<TestApiResponseModel>> = apiService.getPosts()
    override suspend fun getPeople(): Response<List<PeopleResponseItem>> = apiService.getPeople()
    override suspend fun getRooms(): Response<List<RoomsListResponse>> = apiService.getRooms()
}