package com.example.presentation.featuers.patients

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PatientRepoImp
import com.example.domain.model.delete.DeletePatientRemoteModel
import com.example.domain.model.patient.PatientRemoteModel
import com.example.domain.usecase.delete.DeletePatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(
    private val repository: PatientRepoImp,
    private val deletePatientUseCase: DeletePatientUseCase
) : ViewModel() {
    private val patientMutableStateFlow: MutableStateFlow<List<PatientRemoteModel>> =
        MutableStateFlow(emptyList())
    private val deletePatientMutableLiveData: MutableLiveData<DeletePatientRemoteModel> =
        MutableLiveData()
    val deletePatientLiveData: MutableLiveData<DeletePatientRemoteModel> =
        deletePatientMutableLiveData
    private val getPatientMutableStateFlow: MutableStateFlow<PatientRemoteModel?> =
        MutableStateFlow(null)
    val getPatientStateFlow = getPatientMutableStateFlow.asStateFlow()
    private val patientMutableLiveError: MutableStateFlow<Exception?> = MutableStateFlow(null)
    private val patientLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val patientStateFlow = patientMutableStateFlow.asStateFlow()
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


    fun deletePatient(id: String) {
        viewModelScope.launch {
            patientLoadingStateFlow.emit(true)
            try {
                deletePatientMutableLiveData.postValue(deletePatientUseCase(id)!!)
            } catch (e: Exception) {
                patientMutableLiveError.emit(e)
            }
            patientLoadingStateFlow.emit(false)
        }
    }

}