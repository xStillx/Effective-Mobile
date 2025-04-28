package com.arslan.myapplication.features.courses.data.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arslan.myapplication.features.courses.domain.model.Course

@Database(entities = [Course::class], version = 1, exportSchema = false)
abstract class CoursesDataBase: RoomDatabase() {

    abstract fun courseDao(): CoursesDao

    companion object{

        private var INSTANCE: CoursesDataBase? = null

        fun getDataBase(context: Context): CoursesDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoursesDataBase::class.java,
                    "courses_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}