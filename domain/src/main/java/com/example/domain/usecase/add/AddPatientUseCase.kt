package com.example.domain.usecase.add

import com.example.domain.model.add.AddPatientRemoteModel
import com.example.domain.model.add.BodyAddPatientModel
import com.example.domain.repo.PatientRepo
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(bodyAddPatientModel: BodyAddPatientModel):
            AddPatientRemoteModel {
        return repo.addPatient(bodyAddPatientModel)
    }


}