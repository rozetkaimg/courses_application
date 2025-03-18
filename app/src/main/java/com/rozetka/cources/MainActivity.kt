package com.rozetka.cources



import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.rozetka.cources.databinding.ActivityMainBinding
import com.rozetka.cources.ui.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel.isDatabaseEmpty.observe(this) { isEmpty ->
            val startDestination = if (isEmpty) R.id.startFragment else R.id.fragment_home
            val navView: BottomNavigationView = binding.navView
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
            val navController = navHostFragment.navController
            val navInflater = navController.navInflater
            val navGraph = navInflater.inflate(R.navigation.mobile_navigation)
            navGraph.setStartDestination(startDestination)
            if (!isEmpty) {
                val bottomNavigationView = this.window.decorView.rootView?.findViewById<BottomNavigationView>(R.id.nav_view)
                bottomNavigationView?.visibility = View.GONE
                navView.setupWithNavController(navController)
            }
        }
    }
}
