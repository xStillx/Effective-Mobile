package com.arslan.myapplication.features.courses.data.api

import com.arslan.myapplication.features.courses.data.model.CoursesResponse
import retrofit2.http.GET

interface CoursesApi {

    @GET("/")
    suspend fun getCourses(): CoursesResponse
}