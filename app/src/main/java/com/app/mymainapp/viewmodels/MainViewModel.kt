package com.app.mymainapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mymainapp.baseresult.BaseResult
import com.app.mymainapp.baseresult.ResultWrapper
import com.app.mymainapp.localdatabaseservice.entities.StudentEntity
import com.app.mymainapp.models.LoginRequest
import com.app.mymainapp.models.LoginResponses
import com.app.mymainapp.models.TestApiResponseModel
import com.app.mymainapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: AppRepository
) : ViewModel() {

    private val _res = MutableLiveData<BaseResult<List<TestApiResponseModel>>>()
    val res: LiveData<BaseResult<List<TestApiResponseModel>>>
        get() = _res



    private val _insert_student_data=MutableLiveData<Long>()
    val insertStudentData:LiveData<Long>
    get() = _insert_student_data


    private val _get_all_student_data=MutableLiveData<List<StudentEntity>>()
    val GetAllStudentData:LiveData<List<StudentEntity>>
        get() = _get_all_student_data



     fun getPosts() = viewModelScope.launch {
        _res.postValue(BaseResult.loading(null))

        when (val response = mainRepository.getPosts()) {
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

    //For Room Insertion
    fun InsertStudentData(studentEntity: StudentEntity)=viewModelScope.launch {
        _insert_student_data.postValue(mainRepository.insertStudentData(studentEntity))
    }

    //For get inserted Data
    fun GetAllStudentsData()=viewModelScope.launch {
        _get_all_student_data.postValue(mainRepository.fetchStudents())
    }

}