package com.rozetka.cources.ui.start

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rozetka.cources.R
import com.rozetka.cources.databinding.FragmentStartBinding


class StartFragment : Fragment(R.layout.fragment_start) {


    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStartBinding.inflate(inflater, container, false)
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
        val horizontalScrollView = binding.horizontalScrollView
        horizontalScrollView.post {
            val contentWidth = horizontalScrollView.getChildAt(0).width
            val screenWidth = horizontalScrollView.width
            val scrollTo = (contentWidth - screenWidth) / 2
            horizontalScrollView.scrollTo(scrollTo, 0)
        }
        binding.buttonStart.setOnClickListener {

            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host) as NavHostFragment
            val navController = navHostFragment.navController
                navController.navigate(R.id.authFragment)


        }
    }
}
