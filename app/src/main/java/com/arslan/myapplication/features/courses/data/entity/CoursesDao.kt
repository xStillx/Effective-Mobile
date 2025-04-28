package com.arslan.myapplication.features.courses.data.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arslan.myapplication.features.courses.domain.model.Course

@Dao
interface CoursesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCourse(food: Course)

    @Query("SELECT * FROM courses_table ORDER BY id ASC")
    fun readData(): LiveData<List<Course>>

    @Delete
    suspend fun deleteCourse(food: Course)
}