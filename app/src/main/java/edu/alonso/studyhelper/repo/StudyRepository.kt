package edu.alonso.studyhelper.repo

import android.content.Context
import edu.alonso.studyhelper.model.Question
import edu.alonso.studyhelper.model.Subject
import java.util.*

// Singleton class for managing study data
class StudyRepository private constructor(context: Context) {

    // A mutable list to hold all the subjects
    private val subjectList = mutableListOf<Subject>()
    // A mutable map to hold questions for each subject, keyed by subject ID
    private val questionMap = mutableMapOf<Long, MutableList<Question>>()

    // Companion object to implement the singleton pattern
    companion object {
        // The single instance of the StudyRepository
        private var instance: StudyRepository? = null

        // Method to get the single instance of the repository
        fun getInstance(context: Context): StudyRepository {
            // If the instance doesn't exist, create it
            if (instance == null) {
                instance = StudyRepository(context)
            }
            // Return the single instance
            return instance!!
        }
    }
    // Initializer block to add starter data when the repository is created
    init {
        addStarterData()
    }
    // Function to add a new subject
    fun addSubject(subject: Subject) {
        // Add the subject to the list
        subjectList.add(subject)
        // Create an empty list of questions for the new subject
        questionMap[subject.id] = mutableListOf()
    }
    // Function to get an unmodifiable list of all subjects
    fun getSubjects(): List<Subject> {
        return Collections.unmodifiableList(subjectList)
    }
    // Function to add a new question
    fun addQuestion(question: Question) {
        // Add the question to the list of questions for the corresponding subject
        questionMap[question.subjectId]?.add(question)
    }
    // Function to get an unmodifiable list of questions for a given subject
    fun getQuestions(subjectId: Long): List<Question> {
        return Collections.unmodifiableList(questionMap[subjectId]!!)
    }
    // Private function to add some initial data for testing
    private fun addStarterData() {

        // Add a "Math" subject
        addSubject(Subject(1, "Math"))
        // Add a math question
        addQuestion(Question(1, "What is 2 + 3?", "2 + 3 = 5", 1))
        // Add another math question
        addQuestion(Question(2, "What is pi?",
            "The ratio of a circle's circumference to its diameter.", 1))
        // Add a "History" subject
        addSubject(Subject(2, "History"))
        // Add a history question
        addQuestion(Question(3,
            "On what date was the U.S. Declaration of Independence adopted?",
            "July 4, 1776", 2))

        // Add a "Computing" subject
        addSubject(Subject(3, "Computing"))
    }
}