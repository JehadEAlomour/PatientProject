package com.example.domain.usecase.get

import com.example.domain.model.patient.PatientRemoteModel
import com.example.domain.repo.PatientRepo
import javax.inject.Inject

class GetPatientByIdUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(id:String):PatientRemoteModel
    {
        return repo.getPatientById(id)
    }
}