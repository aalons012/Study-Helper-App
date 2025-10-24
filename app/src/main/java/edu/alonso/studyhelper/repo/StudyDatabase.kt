package edu.alonso.studyhelper.repo

// Import Room database classes
import androidx.room.RoomDatabase
import androidx.room.Database
import edu.alonso.studyhelper.model.Question
import edu.alonso.studyhelper.model.Subject

// Annotates the class as a Room database, specifying the entities and version number
@Database(entities = [Question::class, Subject::class], version = 1)
// Abstract class that extends RoomDatabase, providing the database instance
abstract class StudyDatabase : RoomDatabase() {

    // Abstract function that returns the DAO for accessing Question data
    abstract fun questionDao(): QuestionDao
    // Abstract function that returns the DAO for accessing Subject data
    abstract fun subjectDao(): SubjectDao
}