package com.example.data.repository

import com.example.data.api.PatientsApi
import com.example.domain.model.add.AddPatientRemoteModel
import com.example.domain.model.add.BodyAddPatientModel
import com.example.domain.model.delete.DeletePatientRemoteModel

import com.example.domain.model.patient.PatientRemoteModel
import com.example.domain.repo.PatientRepo
import javax.inject.Inject

class PatientRepoImp @Inject constructor(private val api: PatientsApi):PatientRepo {
    override suspend fun getPatient(): List<PatientRemoteModel> {
        return api.getPatient().data
    }

    override suspend fun addPatient(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel {
        return api.addPatient(bodyAddPatientModel)
    }

    override suspend fun deletePatient(id: String): DeletePatientRemoteModel {
        return api.deletePatient(id)
    }


}