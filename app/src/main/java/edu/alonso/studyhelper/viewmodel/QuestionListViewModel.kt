package edu.alonso.studyhelper.viewmodel

import android.app.Application
import edu.alonso.studyhelper.model.Question
import edu.alonso.studyhelper.repo.StudyRepository

class QuestionListViewModel (application: Application) {

    private val studyRepo = StudyRepository.getInstance(application.applicationContext)

    fun getQuestions(subjectId: Long): List<Question> = studyRepo.getQuestions(subjectId)
}