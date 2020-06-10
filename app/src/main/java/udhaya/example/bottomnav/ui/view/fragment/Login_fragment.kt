package udhaya.example.bottomnav.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import udhaya.example.bottomnav.R
import udhaya.example.bottomnav.databinding.FragmentLoginBinding
import udhaya.example.bottomnav.ui.viewModel.Login_ViewModel


class Login_fragment : Fragment() {

    private lateinit var viewModel: Login_ViewModel

    lateinit var button: Button
    private var views: Views? = null

    private class Views(val binding: FragmentLoginBinding)

    var counter: Int = 5
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
        var empty: Int = -1
        viewModel = ViewModelProviders.of(this).get(Login_ViewModel::class.java)
        views?.binding?.apply {
            loginButton.setOnClickListener {
                empty = viewModel.checkBlank(
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
            viewModel.loginresult.observe(viewLifecycleOwner, Observer {
                if (viewModel.loginresult.value == true) {
                    findNavController().navigate(R.id.action_home2_fragment_to_bnv_fragment)
                } else {
                    password_text_input.isErrorEnabled = true
                    password_text_input.error = "Invalid Credentials "

                }

            })
        }
    }
}