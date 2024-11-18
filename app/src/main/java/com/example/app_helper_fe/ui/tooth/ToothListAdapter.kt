package com.example.app_helper_fe.ui.tooth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Tooth
import com.example.app_helper_fe.databinding.ItemToothListBinding


class ToothListAdapter(
    private val items: List<Tooth>,
    private val listener: ToothItemClickListener
) : RecyclerView.Adapter<ToothItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ToothItemViewHolder {
        return ToothItemViewHolder.from(parent,listener)
    }

    override fun onBindViewHolder(holder: ToothItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ToothItemViewHolder(
    private val binding: ItemToothListBinding,
    private val listener: ToothItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tooth: Tooth) {
        itemView.setOnClickListener {
            listener.onToothClick(tooth)
        }
        with(binding) {
            ivToothImage.setImageResource(tooth.medicineResourceId)
            tvToothMedicineName.text = tooth.medicineName
            tvPharmacyName.text = tooth.pharmacyName
            tvPharmacyNumber.text = tooth.pharmacyNumber
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: ToothItemClickListener): ToothItemViewHolder {
            return ToothItemViewHolder(
                ItemToothListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
        }
    }
}
