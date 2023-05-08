package com.example.presentation.featuers.addPatients

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.add.AddPatientResponse
import com.example.domain.model.add.AddPatientRequest
import com.example.domain.usecase.add.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(private val addPatientUseCase: AddPatientUseCase) :
    ViewModel() {
    private val addPatientMutableStateFlow: MutableStateFlow<AddPatientResponse?> =
        MutableStateFlow(null)
    private val addPatientMutableLiveError: MutableStateFlow<Exception?> = MutableStateFlow(null)
    private val addPatientLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val addPatientStateFlow = addPatientMutableStateFlow.asStateFlow()
    val addPatientLiveError: StateFlow<Exception?> = addPatientMutableLiveError.asStateFlow()
    val addPatientLoading: StateFlow<Boolean> = addPatientLoadingStateFlow.asStateFlow()


    fun addPatient(addPatientRequest: AddPatientRequest) {
        viewModelScope.launch {
            addPatientLoadingStateFlow.emit(true)
            try {
                addPatientMutableStateFlow.emit(addPatientUseCase(addPatientRequest))
                Log.d("TAGAdd", "addPatient:Done ")
            } catch (e: Exception) {
                addPatientMutableLiveError.emit(e)
                Log.d("TAG100", "addPatient: ${e.localizedMessage}")

            }
            addPatientLoadingStateFlow.emit(false)
        }

    }


}