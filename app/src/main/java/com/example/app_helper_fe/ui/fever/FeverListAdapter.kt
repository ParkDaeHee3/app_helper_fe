package com.example.app_helper_fe.ui.fever

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.R
import com.example.app_helper_fe.data.Fever
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemFeverListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener


class FeverListAdapter(
    private val items: List<Medicine.Body.Item>,
    private val feverClicklistener: FeverItemClickListener,
    private val detailClickListener: MedicineDetailClickListener

) : RecyclerView.Adapter<FeverItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeverItemViewHolder {
        return FeverItemViewHolder.from(parent, feverClicklistener, detailClickListener)
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
    private val feverClicklistener: FeverItemClickListener,
    private val detailClickListener: MedicineDetailClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine.Body.Item) {
        itemView.setOnClickListener {
            feverClicklistener.onFeverClick(medicine)
            detailClickListener.onMedicineDetailClick()

        }
        with(binding) {
            ivFeverImage.setImageResource(R.drawable.cold)
            tvFeverMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
            tvPharmacyNumber.text = medicine.itemSeq
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            feverClicklistener: FeverItemClickListener,
            detailClickListener: MedicineDetailClickListener
        ): FeverItemViewHolder {
            val binding = ItemFeverListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return FeverItemViewHolder(binding, feverClicklistener, detailClickListener)
        }
    }
}
