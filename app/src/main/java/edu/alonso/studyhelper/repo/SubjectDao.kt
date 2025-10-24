package edu.alonso.studyhelper.repo

import androidx.room.*
import edu.alonso.studyhelper.model.Subject

@Dao
interface SubjectDao {
    @Query("SELECT * FROM Subject WHERE id = :id")
    fun getSubject(id: Long): Subject?

    @Query("SELECT * FROM Subject ORDER BY text COLLATE NOCASE")
    fun getSubjects(): List<Subject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSubject(subject: Subject): Long

    @Update
    fun updateSubject(subject: Subject)

    @Delete
    fun deleteSubject(subject: Subject)
}