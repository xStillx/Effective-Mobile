package com.arslan.myapplication.features.courses.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arslan.myapplication.R
import com.arslan.myapplication.databinding.ItemCourseBinding
import com.arslan.myapplication.features.courses.domain.model.Course
import com.arslan.myapplication.utils.dateFormatter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CoursesAdapter(
    private val courses: List<Course>,
    private val onFavoriteCourseClick: (Course, Boolean) -> Unit
) : RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder>() {


    class CoursesViewHolder(view: View, private val onFavoriteCourseClick: (Course, Boolean) -> Unit) :
        ViewHolder(view) {

        private val viewBinding: ItemCourseBinding by viewBinding(ItemCourseBinding::bind)
        @SuppressLint("SetTextI18n")
        fun bind(course: Course) {
            var isFavorite = false
            if (course.hasLike){
                viewBinding.btnFavorite.setImageResource(R.drawable.ic_is_favorite)
                isFavorite = true
                onFavoriteCourseClick.invoke(course, true)
            }
            viewBinding.tvStartDate.text = course.startDate.dateFormatter()
            viewBinding.tvCourseTitle.text = course.title
            viewBinding.tvCourseDescription.text = course.text
            viewBinding.tvStars.text = course.rate
            viewBinding.tvCoursePrice.text =
                course.price + " " + itemView.resources.getText(R.string.ruble)
            viewBinding.btnFavorite.setOnClickListener {
                if (!isFavorite){
                    viewBinding.btnFavorite.setImageResource(R.drawable.ic_is_favorite)
                    onFavoriteCourseClick(course, true)
                    isFavorite = true
                }else{
                    viewBinding.btnFavorite.setImageResource(R.drawable.ic_not_favorite)
                    onFavoriteCourseClick(course, false)
                    isFavorite = false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val cellForRow =
            LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CoursesViewHolder(cellForRow, onFavoriteCourseClick)
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind(courses[position])
    }

}