package com.example.domain.usecase.add

import com.example.domain.model.add.AddPatientResponse
import com.example.domain.model.add.AddPatientRequest
import com.example.domain.repo.PatientRepo
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(addPatientRequest: AddPatientRequest):
            AddPatientResponse {
        return repo.addPatient(addPatientRequest)
    }


}