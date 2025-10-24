package edu.alonso.studyhelper.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = Subject::class,
        parentColumns = ["id"],
        childColumns = ["subject_id"],
        onDelete = CASCADE)
])

// Data class representing a Question
data class Question (
    // Unique identifier for the question, with a default value of 0
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    // The text of the question, with a default value of an empty string
    var text: String = "",
    // The answer to the question, with a default value of an empty string
    var answer: String = "",
    // The ID of the subject this question belongs to, with a default value of 0
    @ColumnInfo(name = "subject_id")
    var subjectId: Long = 0
)