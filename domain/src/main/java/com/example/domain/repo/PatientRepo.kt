package com.example.domain.repo


import com.example.domain.model.GetPatientWrappedModel
import com.example.domain.model.add.AddPatientRemoteModel
import com.example.domain.model.add.BodyAddPatientModel
import com.example.domain.model.delete.DeletePatientRemoteModel
import com.example.domain.model.patient.PatientRemoteModel

interface PatientRepo {
    suspend fun getPatient():List<PatientRemoteModel>
    suspend fun addPatient( bodyAddPatientModel: BodyAddPatientModel):AddPatientRemoteModel
    suspend fun deletePatient( id: String):DeletePatientRemoteModel
    suspend fun getPatientById(id:String):PatientRemoteModel

}