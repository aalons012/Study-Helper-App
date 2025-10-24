package edu.alonso.studyhelper.repo

import androidx.room.*
import edu.alonso.studyhelper.model.Subject

// Marks the interface as a Data Access Object (DAO) for Room
@Dao
// Defines the interface for accessing Subject data from the database
interface SubjectDao {
    // Defines a query to get a single Subject by its ID
    @Query("SELECT * FROM Subject WHERE id = :id")
    fun getSubject(id: Long): Subject?

    // Defines a query to get a list of all Subjects, ordered by text (case-insensitive)
    @Query("SELECT * FROM Subject ORDER BY text COLLATE NOCASE")
    fun getSubjects(): List<Subject>

    // Defines an insert operation to add a new Subject. If a conflict occurs, the existing subject will be replaced.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSubject(subject: Subject): Long

    // Defines an update operation to modify an existing Subject
    @Update
    fun updateSubject(subject: Subject)

    // Defines a delete operation to remove a Subject from the database
    @Delete
    fun deleteSubject(subject: Subject)
}
