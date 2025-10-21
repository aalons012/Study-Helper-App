package edu.alonso.studyhelper.model

// Data class representing a Subject
data class Subject (
    // Unique identifier for the subject, with a default value of 0
    var id: Long = 0,
    // The text or name/description of the subject
    var text: String,
    // The last update time of the subject, defaults to the current time
    var updateTime: Long = System.currentTimeMillis()
)