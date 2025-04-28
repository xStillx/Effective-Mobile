package com.arslan.myapplication.features.onboarding.presentation

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arslan.myapplication.R
import com.arslan.myapplication.databinding.FragmentOnboardingBinding
import com.arslan.myapplication.features.onboarding.presentation.adapter.CompetenceAdapter
import com.arslan.myapplication.utils.call
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment: Fragment(R.layout.fragment_onboarding) {

    private val viewBinding: FragmentOnboardingBinding by viewBinding(FragmentOnboardingBinding::bind)
    private val viewModel: OnBoardingViewModel by viewModels()

    @SuppressLint("UseKtx")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun initViews() {
        if (isFirstOpen()){
            viewModel.goToLogin.call()
        }
        viewBinding.rvCourses.layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL)
        viewBinding.btnContinue.setOnClickListener{
            viewModel.onContinueClick()
        }
    }

    private fun observe(){
        viewModel.courses.observe(viewLifecycleOwner){
            viewBinding.rvCourses.adapter = CompetenceAdapter(it)
        }
        viewModel.goToLogin.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }
    }

    private fun saveFirstOpen(){
        val sharedPref = requireContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        sharedPref.edit().putBoolean(PREF_SAVE_DATA_NAME, true).apply()
    }

    private fun isFirstOpen(): Boolean{
        val sharedPref = requireContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        return sharedPref.getBoolean(PREF_SAVE_DATA_NAME, false)
    }

    override fun onPause() {
        super.onPause()
        saveFirstOpen()
    }
}

const val PREF_NAME = "Data"
const val PREF_SAVE_DATA_NAME = "FirstOpen"