package com.example.app_helper_fe.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemAllMedicineBinding

class SearchMedicineListAdapter(
    private val items: List<Medicine>,
    private val listener: SearchMedicineItemClickListener
) : RecyclerView.Adapter<SearchMedicineItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchMedicineItemViewHolder {
        return SearchMedicineItemViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: SearchMedicineItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class SearchMedicineItemViewHolder(
    private val binding: ItemAllMedicineBinding,
    private val listener: SearchMedicineItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine) {
        itemView.setOnClickListener {
            listener.onSearchMedicineClick(medicine)
        }
        with(binding) {
            //ivAllMedicineImage.setImageResource(medicine.medicineResourceId)
            //tvMedicineName.text = medicine.medicineName
            //tvPharmacyName.text = medicine.pharmacyName
            //tvPharmacyNumber.text = medicine.pharmacyNumber
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: SearchMedicineItemClickListener): SearchMedicineItemViewHolder {
            return SearchMedicineItemViewHolder(
                ItemAllMedicineBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
        }
    }
}
