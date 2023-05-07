package com.example.data.api


import com.example.domain.model.GetPatientWrappedModel
import com.example.domain.model.add.AddPatientRemoteModel
import com.example.domain.model.add.BodyAddPatientModel
import com.example.domain.model.delete.DeletePatientRemoteModel
import com.example.domain.model.patient.PatientsWrappedRemoteModel
import retrofit2.http.*

interface PatientsApi {
    @GET("patients")
    suspend fun getPatient(): PatientsWrappedRemoteModel

    @POST("patients")
    suspend fun addPatient(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel

    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id: String): DeletePatientRemoteModel

    @GET("patients/{id}")
    suspend fun getPatientById(@Path("id") id: String):GetPatientWrappedModel


}