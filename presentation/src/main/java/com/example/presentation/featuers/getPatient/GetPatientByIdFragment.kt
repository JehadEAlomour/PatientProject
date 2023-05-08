package com.example.presentation.featuers.getPatient

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.imageUrl
import com.example.domain.model.patient.PatientResponse
import com.example.presentation.databinding.FragmentGetPatientByIdBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GetPatientByIdFragment : Fragment() {
    private val viewModel: GetPatientViewModel by viewModels()
private lateinit var binding: FragmentGetPatientByIdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentGetPatientByIdBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initView(model:PatientResponse) {
        binding.ivPhoto.imageUrl(model.photo)
        binding.tvAboutInfo.text=model.getAbout()
        binding.tvName.text=model.name
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientLoading.collect {
                binding.progressCircular.isVisible = it
            }
        }
        lifecycleScope.launch {
            viewModel.patientLiveError.collect {
                if (it != null) {
                    Log.d("TAGErrorFragment", "error:$it ")
                    Toast.makeText(
                        requireContext(),
                        "Error ${it.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
        lifecycleScope.launch {
            viewModel.patientStateFlow.collect {
                if (it != null) {
                    initView(it)
//                    Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }


}