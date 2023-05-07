package com.example.domain.model

import com.example.domain.model.patient.PatientRemoteModel

data class GetPatientWrappedModel (
    val status:Int,
    val message:String,
    val data:PatientRemoteModel
        )