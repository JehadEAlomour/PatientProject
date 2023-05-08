package com.example.domain.usecase.get

import com.example.domain.model.patient.PatientResponse
import com.example.domain.repo.PatientRepo
import javax.inject.Inject

class GetPatientByIdUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(id:String):PatientResponse
    {
        return repo.getPatientById(id)
    }
}