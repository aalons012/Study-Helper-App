package edu.alonso.studyhelper.repo

import androidx.room.*
import edu.alonso.studyhelper.model.Question

@Dao
interface QuestionDao{
    @Query("SELECT * FROM Question WHERE id = :id")
    fun getQuestion(id: Long): Question?

    @Query("SELECT * FROM Question WHERE subject_id = :subjectId ORDER BY id")
    fun getQuestions(subjectId: Long): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuestion(question: Question): Long

    @Update
    fun updateQuestion(question: Question)

    @Delete
    fun deleteQuestion(question: Question)
}