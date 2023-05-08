package com.example.domain.model.patient

import com.google.gson.annotations.SerializedName

data class PatientResponse
    (
    @SerializedName("condition")
    val condition: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("photo")
    val photo: String,
    //local var
//    val tests: List<TestModel>,
    var selected: Boolean = false,

    ) {
    fun getAbout(): String {
        return "Lives in $address , Email $email ,Born on $birthdate"
    }
}
