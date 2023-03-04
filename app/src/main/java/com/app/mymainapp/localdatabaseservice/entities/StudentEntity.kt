package com.app.mymainapp.localdatabaseservice.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val studentId: Long = 0L,
    val fName: String,
    val lName: String,
    val standard: String,
    val age: Int
)