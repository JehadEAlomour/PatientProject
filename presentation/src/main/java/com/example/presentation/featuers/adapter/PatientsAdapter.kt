package com.example.presentation.featuers.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.imageUrl
import com.example.domain.model.patient.PatientRemoteModel
import com.example.presentation.R

class PatientsAdapter(private val onDeletePatient: (id:String) ->Unit)
    :ListAdapter<PatientRemoteModel,PatientsAdapter.PatientsViewHolder>(DiffCallBack){
    class PatientsViewHolder(private val view :View):RecyclerView.ViewHolder(view) {
        val nameTextView:TextView
        val berthdayTextView:TextView
        val conditionTextView:TextView
        val deleteImage:ImageView
        val image:ImageView
        init{
            nameTextView=view.findViewById(R.id.tv_name)
            berthdayTextView=view.findViewById(R.id.date_barthday)
            conditionTextView=view.findViewById(R.id.condition)
            image=view.findViewById(R.id.iv_photo)
            deleteImage=view.findViewById(R.id.iv_delete)
            deleteImage.setOnClickListener{

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_patients, parent, false)
        return PatientsViewHolder(view)
    }



    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        holder.image.imageUrl(getItem(position).photo)
        holder.conditionTextView.text=getItem(position).condition
        holder.nameTextView.text=getItem(position).name
        holder.berthdayTextView.text=getItem(position).birthdate
        holder.deleteImage.setOnClickListener{
            onDeletePatient(getItem(position)._id)
        }

    }
    private object DiffCallBack:DiffUtil.ItemCallback<PatientRemoteModel>()
    {
        override fun areItemsTheSame(
            oldItem: PatientRemoteModel,
            newItem: PatientRemoteModel,
        ): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: PatientRemoteModel,
            newItem: PatientRemoteModel,
        ): Boolean {
            return oldItem==newItem
        }

    }


}