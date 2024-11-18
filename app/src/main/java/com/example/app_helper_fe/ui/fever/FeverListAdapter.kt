package com.example.app_helper_fe.ui.fever

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Fever
import com.example.app_helper_fe.databinding.ItemFeverListBinding


class FeverListAdapter(
    private val items: List<Fever>,
    private val listener: FeverItemClickListener
) : RecyclerView.Adapter<FeverItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeverItemViewHolder {
        return FeverItemViewHolder.from(parent,listener)
    }

    override fun onBindViewHolder(holder: FeverItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class FeverItemViewHolder(
    private val binding: ItemFeverListBinding,
    private val listener: FeverItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(fever:Fever) {
        itemView.setOnClickListener {
            listener.onFeverClick(fever)
        }
        with(binding) {
            ivFeverImage.setImageResource(fever.medicineResourceId)
            tvFeverMedicineName.text = fever.medicineName
            tvPharmacyName.text = fever.pharmacyName
            tvPharmacyNumber.text = fever.pharmacyNumber
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: FeverItemClickListener): FeverItemViewHolder {
            return FeverItemViewHolder(
                ItemFeverListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
        }
    }
}
