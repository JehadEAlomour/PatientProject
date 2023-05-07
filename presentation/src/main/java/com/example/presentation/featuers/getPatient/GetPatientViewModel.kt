package com.example.presentation.featuers.getPatient

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.domain.model.patient.PatientRemoteModel
import com.example.domain.usecase.get.GetPatientByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetPatientViewModel @Inject constructor(
    private val getPatientByIdUseCase: GetPatientByIdUseCase,
    status: SavedStateHandle
) :
    ViewModel() {
    private val patientMutableStateFlow:MutableStateFlow<PatientRemoteModel?> = MutableStateFlow(null)
    private val patientMutableLiveError: MutableStateFlow<Exception?> = MutableStateFlow(null)
    private val patientLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val patientLiveError: StateFlow<Exception?> = patientMutableLiveError.asStateFlow()
    val patientLoading: StateFlow<Boolean> = patientLoadingStateFlow.asStateFlow()
    val patientStateFlow=patientMutableStateFlow.asStateFlow()
    private val savedStateHandle=status
    init{
        details()
    }

    private fun details() {
        val id=savedStateHandle.get<String>("id")?:"-1"
        Log.d("TAGId", "details id:$id ")
        viewModelScope.launch {
            patientLoadingStateFlow.emit(true)
            try {
                patientMutableStateFlow.emit(getPatientByIdUseCase.invoke(id))
            }
            catch (e:Exception)
            {
                Log.d("TAGErrorGetPatientById", "Error is:${e.localizedMessage} ")
                patientMutableLiveError.emit(e)
            }
            patientLoadingStateFlow.emit(false)

        }
    }


}