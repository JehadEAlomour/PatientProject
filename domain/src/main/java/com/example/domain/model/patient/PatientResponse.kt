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
    
    val mobile: String,
    val email: String,
    val birthdate: String,
    val gender: String,
    val photo: String,
    //local var
//    val tests: List<TestModel>,
    var selected: Boolean = false

)
{
        fun getAbout():String
        {
            return "Lives in $address , Email $email ,Born on $birthdate"
        }
    }
