package com.app.mymainapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mymainapp.baseresult.BaseResult
import com.app.mymainapp.baseresult.ResultWrapper
import com.app.mymainapp.models.PeopleResponseItem
import com.app.mymainapp.models.RoomsListResponse
import com.app.mymainapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: AppRepository
) : ViewModel() {

    private val _res = MutableLiveData<BaseResult<List<PeopleResponseItem>>>()
    val res: LiveData<BaseResult<List<PeopleResponseItem>>>
        get() = _res

    private val _rooms = MutableLiveData<BaseResult<List<RoomsListResponse>>>()
    val rooms: LiveData<BaseResult<List<RoomsListResponse>>>
        get() = _rooms



     fun getPeople() = viewModelScope.launch {
        _res.postValue(BaseResult.loading(null))

        when (val response = mainRepository.getPeople()) {
            is ResultWrapper.Success ->
                _res.postValue(
                    BaseResult.success(
                        response.data
                    )
                )

            is ResultWrapper.Failure ->
                _res.postValue(
                    BaseResult.error(
                        response.message
                    )
                )

        }
    }

    fun getRooms() = viewModelScope.launch {
        _rooms.postValue(BaseResult.loading(null))

        when (val response = mainRepository.getRooms()) {
            is ResultWrapper.Success ->
                _rooms.postValue(
                    BaseResult.success(
                        response.data
                    )
                )

            is ResultWrapper.Failure ->
                _rooms.postValue(
                    BaseResult.error(
                        response.message
                    )
                )

        }
    }


}