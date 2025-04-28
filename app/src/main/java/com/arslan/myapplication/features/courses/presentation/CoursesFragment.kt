package com.arslan.myapplication.features.courses.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arslan.myapplication.R
import com.arslan.myapplication.databinding.FragmentCoursesBinding
import com.arslan.myapplication.features.courses.presentation.adapter.CoursesAdapter
import com.arslan.myapplication.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursesFragment: Fragment(R.layout.fragment_courses) {

    private val viewBinding: FragmentCoursesBinding by viewBinding(FragmentCoursesBinding::bind)
    private val viewModel: CoursesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun initViews() {
        viewModel.dataBase(requireContext())
        viewBinding.rvCoursesList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun observe() {
        viewModel.isVisible.observe(viewLifecycleOwner){
            viewBinding.pbProgress.isVisible = it
        }
        viewModel.courses.observe(viewLifecycleOwner){
            when(it){
                ViewState.Empty -> {
                    Toast.makeText(requireContext(), "empty", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    Log.d("FragmentCourses", "${it.throwable} ")
                }
                is ViewState.Show -> {
                    viewBinding.rvCoursesList.adapter = CoursesAdapter(it.data.courses){ it, isFavorite ->
                        if (isFavorite){
                            viewModel.onAddCourseToFavoritesClick(it)
                        }else{
                            viewModel.onDeleteFavoriteClick(it)
                        }
                    }
                }
            }
        }
    }
}

