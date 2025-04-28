package com.arslan.myapplication.features.favorites.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arslan.myapplication.R
import com.arslan.myapplication.databinding.FragmentFavoritesBinding
import com.arslan.myapplication.features.favorites.presentation.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment: Fragment(R.layout.fragment_favorites) {

    private val viewBinding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun observe() {
        viewModel.readAllData!!.observe(viewLifecycleOwner){
            viewBinding.rvFavorites.adapter = FavoritesAdapter(it){ food ->
                viewModel.onDeleteFavoriteClick(food)
            }
        }
    }

    private fun initViews() {
        viewModel.database(requireContext())
        viewBinding.rvFavorites.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}