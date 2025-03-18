package com.rozetka.domain.ext

import com.rozetka.model.CoursesModel
import com.rozetka.storage.CourseEntity

fun CoursesModel.toEntity(): CourseEntity {
    return CourseEntity(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}

fun List<CourseEntity>.toModelList(): List<CoursesModel> {
    return this.map { it.toModel() }
}

fun CourseEntity.toModel(): CoursesModel {
    return CoursesModel(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}
fun List<CoursesModel>.toEntityList(): List<CourseEntity> {
    return this.map { it.toEntity() }
}
