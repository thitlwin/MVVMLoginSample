package com.thit.mvvmloginsampleprj.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.thit.mvvmloginsampleprj.R
import com.thit.mvvmloginsampleprj.data.UserPreferences
import com.thit.mvvmloginsampleprj.databinding.FragmentLoginBinding
import com.thit.mvvmloginsampleprj.data.network.AuthApi
import com.thit.mvvmloginsampleprj.data.network.Resource
import com.thit.mvvmloginsampleprj.data.repository.AuthRepository
import com.thit.mvvmloginsampleprj.ui.base.BaseFragment
import com.thit.mvvmloginsampleprj.ui.enable
import com.thit.mvvmloginsampleprj.ui.home.HomeActivity
import com.thit.mvvmloginsampleprj.ui.startNewActivity
import com.thit.mvvmloginsampleprj.ui.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)
        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().trim().isNotEmpty())
        }

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(false)
            when (it) {
                is Resource.Success -> {
                    viewModel.saveAuthToken(it.value.data[0].token)
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            binding.progressbar.visible(true)

            Log.d("email", email)
            Log.d("password", password)
            viewModel.login(email, password)
        }
    }

//    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java
//
//    override fun getFragmentBinding(
//        inflater: LayoutInflater,
//        container: ViewGroup?
//    ) = FragmentLoginBinding.inflate(inflater, container, false)
//
//    override fun getFragmentRepository(): AuthRepository =
//        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)
}