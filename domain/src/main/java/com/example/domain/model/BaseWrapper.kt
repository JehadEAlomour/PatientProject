package com.example.domain.model

import com.google.gson.annotations.SerializedName


data class BaseWrapper<T>
    (
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T,
)