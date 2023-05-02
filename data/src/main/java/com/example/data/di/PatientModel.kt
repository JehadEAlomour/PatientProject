package com.example.data.di

import com.example.data.api.PatientsApi
import com.example.data.repository.PatientRepoImp
import com.example.domain.repo.PatientRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PatientModel {
    @Provides
    fun providePatient(patientsApi: PatientsApi): PatientRepo {
        return PatientRepoImp(patientsApi)
    }
}