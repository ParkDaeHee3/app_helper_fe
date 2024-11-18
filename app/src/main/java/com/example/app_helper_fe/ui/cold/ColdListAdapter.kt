package com.example.app_helper_fe.ui.cold

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Cold
import com.example.app_helper_fe.databinding.ItemColdListBinding


class ColdListAdapter(
    private val items: List<Cold>,
    private val listener: ColdItemClickListener
) : RecyclerView.Adapter<ColdItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColdItemViewHolder {
        return ColdItemViewHolder.from(parent,listener)
    }

    override fun onBindViewHolder(holder: ColdItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ColdItemViewHolder(
    private val binding: ItemColdListBinding,
    private val listener: ColdItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cold: Cold) {
        itemView.setOnClickListener {
            listener.onColdClick(cold)
            //activity medicine detail를 통한
            val action = ColdMedicineFragmentDirections.actionViewCardColdAreaToMedicineDetail()
            it.findNavController().navigate(action)
        }
        with(binding) {
            ivColdImage.setImageResource(cold.medicineResourceId)
            tvColdMedicineName.text = cold.medicineName
            tvPharmacyName.text = cold.pharmacyName
            tvPharmacyNumber.text = cold.pharmacyNumber
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: ColdItemClickListener): ColdItemViewHolder {
            return ColdItemViewHolder(
                ItemColdListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
        }
    }
}
