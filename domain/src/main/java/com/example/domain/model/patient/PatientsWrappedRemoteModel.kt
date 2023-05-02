package com.example.domain.model.patient

data class PatientsWrappedRemoteModel
    (
    val status: Int,
    val message: String,
    val data: List<PatientRemoteModel>,
)
