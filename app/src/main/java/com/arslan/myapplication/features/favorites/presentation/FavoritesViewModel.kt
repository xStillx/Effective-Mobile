package com.arslan.myapplication.features.favorites.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arslan.myapplication.features.courses.domain.model.Course
import com.arslan.myapplication.features.courses.data.entity.CoursesDataBase
import com.arslan.myapplication.features.courses.data.entity.CoursesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(): ViewModel(){

    var readAllData: LiveData<List<Course>>? = null
    private var repository: CoursesRepository? = null

    fun database(context: Context) {
        val foodDao = CoursesDataBase.getDataBase(context = context).courseDao()
        repository = CoursesRepository(foodDao)
        readAllData = repository!!.readAllData
    }

    fun onDeleteFavoriteClick(course: Course){
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.deleteCourse(course)
        }
    }
}