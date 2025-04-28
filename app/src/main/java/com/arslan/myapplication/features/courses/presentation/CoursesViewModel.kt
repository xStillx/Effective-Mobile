package com.arslan.myapplication.features.courses.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arslan.myapplication.features.courses.domain.CoursesInteractor
import com.arslan.myapplication.features.courses.domain.model.Course
import com.arslan.myapplication.features.courses.domain.model.Courses
import com.arslan.myapplication.features.courses.data.entity.CoursesDataBase
import com.arslan.myapplication.features.courses.data.entity.CoursesRepository
import com.arslan.myapplication.utils.ViewState
import com.arslan.myapplication.utils.asLiveData
import com.arslan.myapplication.utils.asViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val coursesInteractor: CoursesInteractor
): ViewModel() {

    private val _isVisible = MutableLiveData(false)
    val isVisible get() = _isVisible.asLiveData()

    private val _courses = MutableLiveData<ViewState<Courses>>()
    val courses get() = _courses.asLiveData()

    private var repository: CoursesRepository? = null

    init {
        getCourses()
    }

    private fun getCourses(){
        _isVisible.value = true
        viewModelScope.launch {
           _courses.value = coursesInteractor.getCourses().asViewState()
            _isVisible.value = false
        }
    }

    fun dataBase(context: Context){
        val courseDao = CoursesDataBase.getDataBase(context = context).courseDao()
        repository = CoursesRepository(courseDao)
    }

    fun onAddCourseToFavoritesClick(course: Course){
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.addCourse(course)
        }
    }

    fun onDeleteFavoriteClick(course: Course){
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.deleteCourse(course)
        }
    }

}