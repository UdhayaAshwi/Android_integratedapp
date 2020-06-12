package com.udhaya.integratedapp.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.udhaya.integratedapp.R
import com.udhaya.integratedapp.databinding.FragmentLoginBinding
import com.udhaya.integratedapp.ui.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

/* Fragment to perform login screen validation
and navigating to the success page upon successful
validation
* */

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private var views: Views? = null

    private class Views(val binding: FragmentLoginBinding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        views = Views(binding = binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        views?.binding?.apply {
            loginButton.setOnClickListener {
                // when the login button is clicked, perform validation
                val empty = viewModel.checkBlank(
                    username = username.text.toString(),
                    password = password.text.toString()
                )
                if (empty == 1) {
                    username_text_input.error = "Username cannot be empty"
                }
                if (empty == 2) {
                    password_text_input.error = "Password cannot be empty"
                }
                viewModel.performLogin(
                    username = username.text.toString(),
                    password = password.text.toString()
                )
            }
            viewModel.loginResult.observe(viewLifecycleOwner, Observer {
                if (viewModel.loginResult.value == true) {
                    findNavController().navigate(R.id.action_home2_fragment_to_bnv_fragment)
                } else {
                    password_text_input.isErrorEnabled = true
                    password_text_input.error = "Invalid Credentials "
                }

            })
        }
    }
}