package com.example.data.api


import com.example.domain.model.BaseWrapper
import com.example.domain.model.add.AddPatientResponse
import com.example.domain.model.add.AddPatientRequest
import com.example.domain.model.delete.DeletePatientResponse
import com.example.domain.model.patient.PatientResponse
import retrofit2.http.*

interface PatientsApi {
    @GET("patients")
    suspend fun getPatient(): BaseWrapper<List<PatientResponse>>

    @POST("patients")
    suspend fun addPatient(@Body addPatientRequest: AddPatientRequest): AddPatientResponse

    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id: String): DeletePatientResponse

    @GET("patients/{id}")
    suspend fun getPatient(@Path("id") id: String): BaseWrapper<PatientResponse>


}