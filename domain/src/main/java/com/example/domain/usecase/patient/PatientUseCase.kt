package com.example.domain.usecase.patient

import com.example.domain.model.patient.PatientRemoteModel
import com.example.domain.repo.PatientRepo
import javax.inject.Inject

class PatientUseCase @Inject constructor(private val patientRepo: PatientRepo) {
    suspend operator fun invoke(): List<PatientRemoteModel> {
        return patientRepo.getPatient().sortedBy { it.name }
    }
}