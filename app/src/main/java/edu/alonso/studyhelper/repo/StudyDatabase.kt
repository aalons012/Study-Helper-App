package edu.alonso.studyhelper.repo

import androidx.room.RoomDatabase
import androidx.room.Database
import edu.alonso.studyhelper.model.Question
import edu.alonso.studyhelper.model.Subject

@Database(entities = [Question::class, Subject::class], version = 1)
abstract class StudyDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun subjectDao(): SubjectDao
}