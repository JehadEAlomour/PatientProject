package com.example.domain.repo


import com.example.domain.model.add.AddPatientResponse
import com.example.domain.model.add.AddPatientRequest
import com.example.domain.model.delete.DeletePatientResponse
import com.example.domain.model.patient.PatientResponse

interface PatientRepo {
    suspend fun getPatient():List<PatientResponse>
    suspend fun addPatient(addPatientRequest: AddPatientRequest):AddPatientResponse
    suspend fun deletePatient( id: String):DeletePatientResponse
    suspend fun getPatientById(id:String):PatientResponse

}