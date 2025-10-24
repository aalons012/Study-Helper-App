package edu.alonso.studyhelper.repo

import androidx.room.*
import edu.alonso.studyhelper.model.Question

// Marks the interface as a Data Access Object (DAO) for Room
@Dao
// Defines the interface for accessing Question data from the database
interface QuestionDao{
    // Defines a query to get a single Question by its ID
    @Query("SELECT * FROM Question WHERE id = :id")
    fun getQuestion(id: Long): Question?

    // Defines a query to get a list of all Questions for a given subject, ordered by ID
    @Query("SELECT * FROM Question WHERE subject_id = :subjectId ORDER BY id")
    fun getQuestions(subjectId: Long): List<Question>

    // Defines an insert operation to add a new Question. If a conflict occurs, the existing question will be replaced.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuestion(question: Question): Long

    // Defines an update operation to modify an existing Question
    @Update
    fun updateQuestion(question: Question)

    // Defines a delete operation to remove a Question from the database
    @Delete
    fun deleteQuestion(question: Question)
}