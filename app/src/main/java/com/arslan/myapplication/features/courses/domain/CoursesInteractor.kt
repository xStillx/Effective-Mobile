package com.arslan.myapplication.features.courses.domain

import com.arslan.myapplication.features.courses.data.CoursesRepo
import com.arslan.myapplication.utils.safeRequest
import javax.inject.Inject

class CoursesInteractor @Inject constructor(
    private val coursesRepo: CoursesRepo
) {

    suspend fun getCourses() = safeRequest {
        coursesRepo.getCourses()
    }
}