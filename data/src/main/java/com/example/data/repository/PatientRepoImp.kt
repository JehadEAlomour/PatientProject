package com.example.data.repository

import com.example.data.api.PatientsApi
import com.example.domain.model.add.AddPatientResponse
import com.example.domain.model.add.AddPatientRequest
import com.example.domain.model.delete.DeletePatientResponse

import com.example.domain.model.patient.PatientResponse
import com.example.domain.repo.PatientRepo
import javax.inject.Inject

class PatientRepoImp @Inject constructor(private val api: PatientsApi):PatientRepo {
    override suspend fun getPatient(): List<PatientResponse> {
        return api.getPatient().data
    }

    override suspend fun addPatient(addPatientRequest: AddPatientRequest): AddPatientResponse {
        return api.addPatient(addPatientRequest)
    }

    override suspend fun deletePatient(id: String): DeletePatientResponse {
        return api.deletePatient(id)
    }

    override suspend fun getPatientById(id: String): PatientResponse {
        return api.getPatient(id).data
    }


}