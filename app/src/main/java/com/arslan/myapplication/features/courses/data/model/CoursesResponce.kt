package com.arslan.myapplication.features.courses.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoursesResponse(
    val courses: List<CourseResponse>
)