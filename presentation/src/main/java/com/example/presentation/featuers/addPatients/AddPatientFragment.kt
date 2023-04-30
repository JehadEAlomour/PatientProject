package com.example.presentation.featuers.addPatients

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
import com.example.domain.model.add.BodyAddPatientModel
import com.example.presentation.databinding.FragmentAddPatientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPatientFragment : Fragment() {
    private lateinit var binding: FragmentAddPatientBinding
    private val viewModel: AddPatientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initListener()
    }

    private fun isValid(): Boolean {
        var valid = true
        if (binding.etGender.text?.isEmpty() == true) {
            valid = false
            binding.tvGender.error = "Gender is empty"
        }

        if (binding.etName.text?.isEmpty() == true) {
            valid = false
            binding.tvName.error = "Name is empty"
        }
        if (binding.etEmail.text?.isEmpty() == true) {
            valid = false
            binding.tvEmail.error = "Email is empty"
        }
        if (binding.etAddress.text?.isEmpty() == true) {
            valid = false
            binding.tvAddress.error = "Address is empty"
        }
        if (binding.etMobile.text?.isEmpty() == true) {
            valid = false
            binding.tvMobile.error = "Mobile is empty"
        }

        return valid
    }

    private fun getInfo(): BodyAddPatientModel {
        return BodyAddPatientModel(
            binding.etName.text.toString(),
            binding.etAddress.text.toString(),
            binding.etGender.text.toString(),
            binding.etBirthday.text.toString(),
            binding.etMobile.text.toString(),
            binding.etEmail.text.toString(),


            )

    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener {

            if (isValid()) {
                val body = getInfo()
                viewModel.addPatient(body)

            }

        }

    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.addPatientStateFlow.collect {
                if (it != null)
                {
                    Log.d("TAGDone", "initObserver:Done ")
                    Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
                    }
            }

        }
        lifecycleScope.launch {
            viewModel.addPatientLoading.collect {
                binding.progressCircular.isVisible = it

            }
        }
        lifecycleScope.launch {
            viewModel.addPatientLiveError.collect {
                if (it != null) {
                    Toast.makeText(
                        requireContext(), "Error:${it.localizedMessage}", Toast.LENGTH_SHORT
                    ).show()
                    Log.d("TAGErrorObserver", "initObserver:${it.localizedMessage} ")
                }
            }
        }
    }


}