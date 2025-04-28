package com.arslan.myapplication.features.onboarding.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arslan.myapplication.R
import com.arslan.myapplication.databinding.ItemCompetenceBinding

class CompetenceAdapter(
    private val courses: List<String>
): RecyclerView.Adapter<CompetenceAdapter.CustomViewHolder>() {

    class CustomViewHolder(view: View): ViewHolder(view) {
        private val viewBinding: ItemCompetenceBinding by viewBinding(ItemCompetenceBinding::bind)
        private var isClick = false

        fun bind(course: String){
            viewBinding.tvCourseName.text = course
            viewBinding.courseMain.setOnClickListener {
                if (!isClick){
                    it.setBackgroundResource(R.drawable.button_background)
                    it.rotation = -15f
                    isClick = true
                }else{
                    it.setBackgroundResource(R.drawable.item_competence_background)
                    it.rotation = 0f
                    isClick = false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val cellForRow = LayoutInflater.from(parent.context).inflate(R.layout.item_competence, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(courses[position])
    }
}