package com.example.presentation.featuers.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.delete.DeletePatientRemoteModel
import com.example.presentation.R
import com.example.presentation.databinding.FragmentPatientBinding
import com.example.presentation.featuers.adapter.PatientsAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientFragment : Fragment() {
    private lateinit var binding: FragmentPatientBinding
    private val patientViwModel: PatientsViewModel by viewModels()
    private lateinit var adapter: PatientsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPatientBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObserver()
        initListener()


    }

    private fun initAdapter() {
        adapter=PatientsAdapter(::deletePatient)
        binding.rvPathinet.adapter=adapter
    }

    private fun initListener() {
        binding.ftbnAdd.setOnClickListener {
            findNavController().navigate(R.id.addPatientFragment)
        }
        binding.swipeRefresh.setOnRefreshListener {
            patientViwModel.getPatient()
        lifecycleScope.launch {
            delay(300)
            binding.swipeRefresh.isRefreshing=false


        }}

    }

    fun initObserver() {
        lifecycleScope.launch {
            patientViwModel.patientLoading.collect {
                binding.progressCircular.isVisible = it
            }
        }
        lifecycleScope.launch {
            patientViwModel.patientStateFlow.collect {
                if (it.isNotEmpty()) {
                    adapter.submitList(it)
                    Log.d("TAG152", "initMutableStateFlow:${it.get(it.size - 1).name} ")
                }
            }

        }
        lifecycleScope.launch {
            patientViwModel.patientLiveError.collect {
                if (it != null)
                    Toast.makeText(
                        requireContext(),
                        "Error is:${it.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
        lifecycleScope.launch {
            patientViwModel.deletePatientLiveData.observe(viewLifecycleOwner,::onSuccessDeletePatient)


            }
        }

    private fun onSuccessDeletePatient(response:DeletePatientRemoteModel)
    {
            if(response!=null)
            {
                Toast.makeText(requireContext(), "Item Is Deleted", Toast.LENGTH_SHORT).show()
                patientViwModel.getPatient()
            }
    }
    private fun deletePatient(id:String)
    {
        MaterialAlertDialogBuilder(requireContext()).setMessage("Are you Sure?").setNegativeButton("No") {daialog,_->
            daialog.dismiss()

        }.setPositiveButton("Yes"){dialoge,_ ->
            patientViwModel.deletePatient(id)
            dialoge.dismiss()
        }.show()
        }}
