package com.example.domain.usecase.patient

import com.example.domain.model.patient.PatientResponse
import com.example.domain.repo.PatientRepo
import javax.inject.Inject

class PatientUseCase @Inject constructor(private val patientRepo: PatientRepo) {
    suspend operator fun invoke(): List<PatientResponse> {
        return patientRepo.getPatient().sortedBy { it.name }
    }
}