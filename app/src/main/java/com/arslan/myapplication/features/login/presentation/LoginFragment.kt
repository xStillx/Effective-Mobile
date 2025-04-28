package com.arslan.myapplication.features.login.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arslan.myapplication.R
import com.arslan.myapplication.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewBinding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun observe() {
        viewModel.goToMain.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
        viewModel.goToVk.observe(viewLifecycleOwner) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(it.toUri())
            startActivity(intent)
        }
        viewModel.goToOk.observe(viewLifecycleOwner) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(it.toUri())
            startActivity(intent)
        }
        viewModel.showFillTheField.observe(viewLifecycleOwner) {
            Snackbar.make(viewBinding.loginScreen, R.string.fill_the_fields, Snackbar.LENGTH_SHORT)
                .show()
        }
        viewModel.showEnterValidEmail.observe(viewLifecycleOwner) {
            Snackbar.make(viewBinding.loginScreen, R.string.enter_valid_email, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun initViews() {
        viewBinding.btnLogin.setOnClickListener {
            viewModel.onLoginClick(
                email = viewBinding.etEmail.text.toString(),
                password = viewBinding.etPassword.text.toString()
            )
        }
        viewBinding.btnVk.setOnClickListener {
            viewModel.onVkCLick()
        }
        viewBinding.btnOk.setOnClickListener {
            viewModel.onOkCLick()
        }
        viewBinding.tvRegistration.setOnClickListener {

        }
        viewBinding.tvForgotPassword.setOnClickListener {

        }
    }
}