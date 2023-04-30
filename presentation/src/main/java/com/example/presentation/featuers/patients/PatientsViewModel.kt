package com.example.presentation.featuers.patients

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PatientRepoImp
import com.example.domain.model.patient.PatientRemoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val repository: PatientRepoImp) : ViewModel() {
    private val patientMutableStateFlow: MutableStateFlow<List<PatientRemoteModel>> =
        MutableStateFlow(emptyList())
    private val patientMutableLiveError: MutableStateFlow<Exception?> = MutableStateFlow(null)
    private val patientLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val patientStateFlow= patientMutableStateFlow.asStateFlow()
    val patientLiveError: StateFlow<Exception?> = patientMutableLiveError.asStateFlow()
    val patientLoading: StateFlow<Boolean> = patientLoadingStateFlow.asStateFlow()

    init {
        this.getPatient()
    }

     fun getPatient() {
        viewModelScope.launch {
            patientLoadingStateFlow.emit(true)
            try {
                patientMutableStateFlow.emit(repository.getPatient())
                Log.d("TAG152", "getPatient:${patientMutableStateFlow.value.size} ")
            } catch (e: Exception) {
                Log.d("TAG152", "getPatient: ${e.localizedMessage}")
                patientMutableLiveError.emit(e)
            }
            patientLoadingStateFlow.emit(false)

        }
    }

}