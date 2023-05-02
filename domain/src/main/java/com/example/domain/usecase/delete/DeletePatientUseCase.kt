package com.example.domain.usecase.delete

import com.example.domain.model.delete.DeletePatientRemoteModel
import com.example.domain.repo.PatientRepo
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(id: String): DeletePatientRemoteModel {
        return repo.deletePatient(id)
    }
}