package com.example.app_helper_fe.ui.cold

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.R
import com.example.app_helper_fe.data.Cold
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemColdListBinding


class ColdListAdapter(
    private val items: List<Medicine.Body.Item>,
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
        return items.count()
    }

}

class ColdItemViewHolder(
    private val binding: ItemColdListBinding,
    private val listener: ColdItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine.Body.Item) {
        itemView.setOnClickListener {
            listener.onColdClick(medicine)
            //activity medicine detail를 통한
            val action = ColdMedicineFragmentDirections.actionViewCardColdAreaToMedicineDetail()
            it.findNavController().navigate(action)
        }
        with(binding) {
            ivColdImage.setImageResource(R.drawable.cold)
            tvColdMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
            tvPharmacyNumber.text = medicine.itemSeq
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
