package com.rozetka.cources.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rozetka.cources.R
import com.rozetka.cources.databinding.ItemCourseBinding
import com.rozetka.cources.ext.formatDateString
import com.rozetka.model.CoursesModel

class CourseAdapter(private val onHasLike: (id: Int, hasLike: Boolean) -> Unit) :
    ListAdapter<CoursesModel, CourseAdapter.CourseViewHolder>(CourseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding, onHasLike)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)

        holder.bind(course)
    }


    class CourseViewHolder(
        private val binding: ItemCourseBinding,
        val onHasLike: (id: Int, hasLike: Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(course: CoursesModel) {
            binding.imageView.setImageResource(getImageResourceByCourseName(course.title))
            binding.title.text = course.title
            binding.description.text = course.text
            binding.price.text = "${course.price} ₽"
            binding.counts.text = course.rate.toString()
            binding.dataText.text = formatDateString(course.startDate)


            updateLikeButtonState(course.hasLike)

            binding.bookStatus.setOnClickListener {
                val newHasLike = !course.hasLike
                course.hasLike = newHasLike
                updateLikeButtonState(newHasLike)
                onHasLike(course.id, newHasLike)
            }
        }

        private fun updateLikeButtonState(hasLike: Boolean) {
            if (hasLike) {
                binding.bookStatus.setImageResource(R.drawable.bookfiil)
                binding.bookStatus.setColorFilter("#12B956".toColorInt())
            } else {
                binding.bookStatus.setImageResource(R.drawable.bookmark)
                binding.bookStatus.setColorFilter("#FFFFFF".toColorInt())
            }
        }
    }

    class CourseDiffCallback : DiffUtil.ItemCallback<CoursesModel>() {
        override fun areItemsTheSame(oldItem: CoursesModel, newItem: CoursesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CoursesModel, newItem: CoursesModel): Boolean {
            return oldItem == newItem
        }
    }
}

fun getImageResourceByCourseName(courseName: String): Int {
    return when (courseName) {
        "3D-дженералист" -> R.drawable.cover
        "Java-разработчик с нуля" -> R.drawable.java
        "Python Advanced. Для продвинутых" -> R.drawable.en
        else -> R.drawable.cover
    }
}
