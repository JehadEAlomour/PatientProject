package com.example.domain.usecase.delete

import com.example.domain.model.delete.DeletePatientResponse
import com.example.domain.repo.PatientRepo
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(id: String): DeletePatientResponse {
        return repo.deletePatient(id)
    }
}