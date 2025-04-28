package com.arslan.myapplication.features.favorites.presentation.adapter

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

class FavoritesAdapter(
    private val courses: List<Course>,
    private val onDeleteFavoriteCourseClick: (Course) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {


    class FavoritesViewHolder(view: View, private val onFavoriteCourseClick: (Course) -> Unit) :
        ViewHolder(view) {

        private val viewBinding: ItemCourseBinding by viewBinding(ItemCourseBinding::bind)

        @SuppressLint("SetTextI18n")
        fun bind(course: Course) {
            viewBinding.btnFavorite.setImageResource(R.drawable.ic_is_favorite)
            viewBinding.tvCourseTitle.text = course.title
            viewBinding.tvCourseDescription.text = course.text
            viewBinding.tvStars.text = course.rate
            viewBinding.tvStartDate.text = course.startDate.dateFormatter()
            viewBinding.tvCoursePrice.text =
                course.price + " " + itemView.resources.getText(R.string.ruble)
            viewBinding.btnFavorite.setOnClickListener {
                onFavoriteCourseClick.invoke(course)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val cellForRow =
            LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return FavoritesViewHolder(cellForRow, onDeleteFavoriteCourseClick)
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(courses[position])
    }
}