package edu.alonso.studyhelper.viewmodel

import android.app.Application
import edu.alonso.studyhelper.model.Subject
import edu.alonso.studyhelper.repo.StudyRepository

class SubjectListViewModel(application: Application) {

    private val studyRepo = StudyRepository.getInstance(application.applicationContext)

    fun getSubjects(): List<Subject> = studyRepo.getSubjects()

    fun addSubject(subject: Subject) = studyRepo.addSubject(subject)
}