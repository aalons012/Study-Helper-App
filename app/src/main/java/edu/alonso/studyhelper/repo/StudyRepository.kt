package edu.alonso.studyhelper.repo

import android.content.Context
import androidx.room.Room
import edu.alonso.studyhelper.model.Question
import edu.alonso.studyhelper.model.Subject
import java.util.*

// Singleton repository for managing all app data from the Room database
class StudyRepository private constructor(context: Context) {

    // Companion object to implement the singleton pattern, ensuring only one instance of the repository exists
    companion object {
        // The single, nullable instance of the repository
        private var instance: StudyRepository? = null

        // Method to get the single instance of the repository, creating it if it doesn't exist
        fun getInstance(context: Context): StudyRepository {
            // If the instance is null, create a new one
            if (instance == null) {
                instance = StudyRepository(context)
            }
            // Return the non-null instance
            return instance!!
        }
    }

    // Builds the Room database instance
    private val database : StudyDatabase = Room.databaseBuilder(
        // Application context to ensure a single database instance
        context.applicationContext,
        // The database class
        StudyDatabase::class.java,
        // The name of the database file
        "study.db"
    )
        // Allows database operations on the main thread (not recommended for production apps)
        .allowMainThreadQueries()
        // Creates the database instance
        .build()

    // Gets a reference to the Subject DAO from the database
    private val subjectDao = database.subjectDao()
    // Gets a reference to the Question DAO from the database
    private val questionDao = database.questionDao()

    // Initializer block that runs when the repository is first created
    init {
        // If there are no subjects in the database, add some starter data
        if (subjectDao.getSubjects().isEmpty()) {
            addStarterData()
        }
    }

    // Fetches a single subject by its ID
    fun getSubject(subjectId: Long): Subject? = subjectDao.getSubject(subjectId)

    // Fetches a list of all subjects
    fun getSubjects(): List<Subject> = subjectDao.getSubjects()

    // Adds a new subject to the database and updates its ID
    fun addSubject(subject: Subject) {
        subject.id = subjectDao.addSubject(subject)
    }

    // Deletes a subject from the database
    fun deleteSubject(subject: Subject) = subjectDao.deleteSubject(subject)

    // Fetches a single question by its ID
    fun getQuestion(questionId: Long): Question? = questionDao.getQuestion(questionId)

    // Fetches all questions for a given subject ID
    fun getQuestions(subjectId: Long): List<Question> = questionDao.getQuestions(subjectId)

    // Adds a new question to the database and updates its ID
    fun addQuestion(question: Question) {
        question.id = questionDao.addQuestion(question)
    }

    // Updates an existing question in the database
    fun updateQuestion(question: Question) = questionDao.updateQuestion(question)

    // Deletes a question from the database
    fun deleteQuestion(question: Question) = questionDao.deleteQuestion(question)

    // Private function to populate the database with initial data if it's empty
    private fun addStarterData() {
        // Add a "Math" subject and get its new ID
        var subjectId = subjectDao.addSubject(Subject(text = "Math"))
        // Add a question for the Math subject
        questionDao.addQuestion(
            Question(
                text = "What is 2 + 3?",
                answer = "2 + 3 = 5",
                subjectId = subjectId
            )
        )
        // Add another question for the Math subject
        questionDao.addQuestion(
            Question(
                text = "What is pi?",
                answer = "The ratio of a circle's circumference to its diameter.",
                subjectId = subjectId
            )
        )

        // Add a "History" subject and get its new ID
        subjectId = subjectDao.addSubject(Subject(text = "History"))
        // Add a question for the History subject
        questionDao.addQuestion(
            Question(
                text = "On what date was the U.S. Declaration of Independence adopted?",
                answer = "July 4, 1776",
                subjectId = subjectId
            )
        )

        // Add a "Computing" subject
        subjectDao.addSubject(Subject(text = "Computing"))
    }
}
