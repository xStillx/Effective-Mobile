package com.arslan.myapplication.features.courses.data.mapper

import com.arslan.myapplication.features.courses.data.model.CoursesResponse
import com.arslan.myapplication.features.courses.domain.model.Courses
import javax.inject.Inject

class CoursesMapper @Inject constructor(
    private val courseMapper: CourseMapper
) {

    fun map(coursesResponse: CoursesResponse) = Courses(
        courses = coursesResponse.courses.map {
            courseMapper.map(it)
        }
    )
}