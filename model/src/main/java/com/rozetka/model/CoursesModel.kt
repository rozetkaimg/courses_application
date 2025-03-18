package com.rozetka.model

data class CoursesModel(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: Double,
    val startDate: String,
    var hasLike: Boolean,
    val publishDate: String
)