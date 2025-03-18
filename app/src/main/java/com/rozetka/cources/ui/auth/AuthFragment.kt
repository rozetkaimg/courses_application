package com.rozetka.cources.ui.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rozetka.cources.R
import com.rozetka.cources.databinding.FragmentAuthBinding
import com.rozetka.cources.databinding.FragmentStartBinding
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

class AuthFragment : Fragment(R.layout.fragment_start) {

val viewModel: AuthViewModel by viewModels()

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView?.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()


        val emailEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText
        val loginButton = binding.loginButton

        viewModel.isLoginButtonEnabled.observe(viewLifecycleOwner, Observer { isEnabled ->
            loginButton.isEnabled = isEnabled
        })

        emailEditText.addTextChangedListener {
            viewModel.onEmailChanged(it.toString())
        }

        passwordEditText.addTextChangedListener {
            viewModel.onPasswordChanged(it.toString())
        }

        loginButton.setOnClickListener {
            viewModel.onLoginClicked()

        }
        binding.vkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, "https://vk.com/".toUri())
            startActivity(intent)

        }
        binding.okButton.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, "https://ok.ru/".toUri())
            startActivity(intent)

        }
        binding.loginButton.setOnClickListener {

            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.navigation_home)


        }
    }
}
