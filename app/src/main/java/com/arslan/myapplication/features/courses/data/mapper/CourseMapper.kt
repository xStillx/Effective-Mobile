package com.arslan.myapplication.features.courses.data.mapper

import com.arslan.myapplication.features.courses.data.model.CourseResponse
import com.arslan.myapplication.features.courses.domain.model.Course
import javax.inject.Inject

class CourseMapper @Inject constructor() {

    fun map(courseResponse: CourseResponse) = Course(
        id = courseResponse.id,
        title = courseResponse.title,
        text = courseResponse.text,
        price = courseResponse.price,
        rate = courseResponse.rate,
        startDate = courseResponse.startDate,
        hasLike = courseResponse.hasLike,
        publishDate = courseResponse.publishDate
    )
}