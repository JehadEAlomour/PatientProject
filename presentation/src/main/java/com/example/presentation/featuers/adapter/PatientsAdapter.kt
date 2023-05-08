package com.example.presentation.featuers.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.imageUrl
import com.example.domain.model.patient.PatientResponse
import com.example.presentation.R

class PatientsAdapter(
    private val onDeletePatient: (id: String) -> Unit,
    private val onClickItem: (id: String) -> Unit,
) : ListAdapter<PatientResponse, PatientsAdapter.PatientsViewHolder>(DiffCallBack) {
    var lastIndex = -1

    class PatientsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        val berthdayTextView: TextView
        val conditionTextView: TextView
        val deleteImage: ImageView
        val image: ImageView
        val cardView: CardView

        init {
            nameTextView = view.findViewById(R.id.tv_name)
            berthdayTextView = view.findViewById(R.id.date_barthday)
            conditionTextView = view.findViewById(R.id.condition)
            image = view.findViewById(R.id.iv_photo)
            deleteImage = view.findViewById(R.id.iv_delete)
            cardView = view.findViewById(R.id.cv_pathinets)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_patients, parent, false)
        return PatientsViewHolder(view)
    }


    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        holder.image.imageUrl(getItem(position).photo)
        holder.conditionTextView.text = getItem(position).condition
        holder.nameTextView.text = getItem(position).name
        holder.berthdayTextView.text = getItem(position).birthdate
        holder.deleteImage.setOnClickListener {
            onDeletePatient(getItem(position).id)
        }
        holder.cardView.setOnClickListener {
            if (position != lastIndex) {
                if (lastIndex != -1) {
                    getItem(lastIndex).selected = false
                    notifyItemChanged(lastIndex)


                }
                lastIndex = position
                getItem(position).selected = true
                notifyItemChanged(position)
            }
            onClickItem(getItem(position).id)


        }

    }

    private object DiffCallBack : DiffUtil.ItemCallback<PatientResponse>() {
        override fun areItemsTheSame(
            oldItem: PatientResponse,
            newItem: PatientResponse,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PatientResponse,
            newItem: PatientResponse,
        ): Boolean {
            return oldItem == newItem
        }

    }


}