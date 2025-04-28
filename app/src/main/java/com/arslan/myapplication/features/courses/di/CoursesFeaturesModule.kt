package com.arslan.myapplication.features.courses.di

import com.arslan.myapplication.features.courses.data.api.CoursesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class CoursesFeaturesModule {

    @Provides
    fun provideCoursesApi(retrofit: Retrofit) = retrofit.create(
        CoursesApi::class.java
    )
}