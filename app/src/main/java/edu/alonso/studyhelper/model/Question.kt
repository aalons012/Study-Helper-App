package edu.alonso.studyhelper.model

// Data class representing a Question
data class Question (
    // Unique identifier for the question, with a default value of 0
    var id: Long = 0,
    // The text of the question, with a default value of an empty string
    var text: String = "",
    // The answer to the question, with a default value of an empty string
    var answer: String = "",
    // The ID of the subject this question belongs to, with a default value of 0
    var subjectId: Long = 0
)