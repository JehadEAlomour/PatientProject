package com.example.domain.model.patient

import com.example.domain.model.TestModel

data class PatientRemoteModel
    (
    val condition: String,
    val _id: String,
    val name: String,
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
