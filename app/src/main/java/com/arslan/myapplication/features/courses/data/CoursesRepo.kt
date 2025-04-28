package com.arslan.myapplication.features.courses.data

import com.arslan.myapplication.features.courses.data.api.CoursesApi
import com.arslan.myapplication.features.courses.data.mapper.CoursesMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoursesRepo @Inject constructor(
    private val api: CoursesApi,
    private val coursesMapper: CoursesMapper
) {

    suspend fun getCourses() = withContext(Dispatchers.IO){
        api.getCourses().let {
            coursesMapper.map(it)
        }
    }
}