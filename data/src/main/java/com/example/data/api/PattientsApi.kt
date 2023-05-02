package com.example.data.api


import com.example.domain.model.add.AddPatientRemoteModel
import com.example.domain.model.add.BodyAddPatientModel
import com.example.domain.model.delete.DeletePatientRemoteModel
import com.example.domain.model.patient.PatientsWrappedRemoteModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientsApi {
    @GET("patients")
    suspend fun getPatient(): PatientsWrappedRemoteModel

    @POST("patients")
    suspend fun addPatient(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel

    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id: String): DeletePatientRemoteModel


}