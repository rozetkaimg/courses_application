package com.rozetka.cources.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rozetka.cources.R
import com.rozetka.cources.ext.parseDate
import com.rozetka.model.CoursesModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: CourseAdapter
    private var coursesList: List<CoursesModel> = emptyList()
    private var isSortedDescending = false
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView?.visibility = View.VISIBLE

        adapter = CourseAdapter { courseId, hasLike ->
            viewModel.hasLikeCourse(courseId, hasLike)
        }

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val sortButton = view.findViewById<TextView>(R.id.sortTextView)
        sortButton.setOnClickListener {
            toggleSortOrder()
        }

        progressBar = view.findViewById(R.id.load_indic)


        progressBar.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.courses.collect { courses ->
                    coursesList = courses
                    updateAdapter()
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun toggleSortOrder() {
        isSortedDescending = !isSortedDescending

        updateAdapter()
    }

    private fun updateAdapter() {
        val sortedList = if (isSortedDescending) {
            coursesList.sortedByDescending { parseDate(it.publishDate) }
        } else {
            coursesList.sortedBy { parseDate(it.publishDate) }
        }
        adapter.submitList(sortedList)
    }


}