package com.arslan.myapplication.features.main.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arslan.myapplication.R
import com.arslan.myapplication.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment(R.layout.fragment_main) {

    private val viewBinding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.coursesFragment -> {
                    viewBinding.fragmentContainerMain.findNavController().navigate(R.id.coursesFragment)
                    true
                }
                R.id.favoritesFragment -> {
                    viewBinding.fragmentContainerMain.findNavController().navigate(R.id.favoritesFragment)
                    true
                }
                R.id.profileFragment -> {
                    viewBinding.fragmentContainerMain.findNavController().navigate(R.id.profileFragment)
                    true
                }
                else -> false
            }
        }
    }

}