package com.example.app_helper_fe.ui.stomach

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Stomach
import com.example.app_helper_fe.databinding.ItemStomachListBinding


class StomachListAdapter(
    private val items: List<Stomach>,
    private val listener: StomachItemClickListener
) : RecyclerView.Adapter<StomachItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StomachItemViewHolder {
        return StomachItemViewHolder.from(parent,listener)
    }

    override fun onBindViewHolder(holder: StomachItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class StomachItemViewHolder(
    private val binding: ItemStomachListBinding,
    private val listener: StomachItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stomach:Stomach) {
        itemView.setOnClickListener {
            listener.onStomachClick(stomach)
        }
        with(binding) {
            ivStomachImage.setImageResource(stomach.medicineResourceId)
            tvStomachMedicineName.text = stomach.medicineName
            tvPharmacyName.text = stomach.pharmacyName
            tvPharmacyNumber.text = stomach.pharmacyNumber
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: StomachItemClickListener): StomachItemViewHolder {
            return StomachItemViewHolder(
                ItemStomachListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
        }
    }
}
