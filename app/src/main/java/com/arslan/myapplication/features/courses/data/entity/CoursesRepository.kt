package com.arslan.myapplication.features.courses.data.entity

import androidx.lifecycle.LiveData
import com.arslan.myapplication.features.courses.domain.model.Course

class CoursesRepository(private val coursesDao: CoursesDao) {

    val readAllData: LiveData<List<Course>> = coursesDao.readData()

    suspend fun addCourse(course: Course){
        coursesDao.addCourse(course)
    }

    suspend fun deleteCourse(course: Course){
        coursesDao.deleteCourse(course)
    }
}