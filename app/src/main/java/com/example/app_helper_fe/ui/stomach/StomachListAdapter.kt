package com.example.app_helper_fe.ui.stomach

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.R
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.data.Stomach
import com.example.app_helper_fe.databinding.ItemStomachListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener


class StomachListAdapter(
    private val items: List<Medicine.Body.Item>,
    private val stomachClicklistener: StomachItemClickListener,
    private val detailClickListener: MedicineDetailClickListener

) : RecyclerView.Adapter<StomachItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StomachItemViewHolder {
        return StomachItemViewHolder.from(parent, stomachClicklistener, detailClickListener)
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
    private val stomachClicklistener: StomachItemClickListener,
    private val detailClickListener: MedicineDetailClickListener

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine.Body.Item) {
        itemView.setOnClickListener {
            stomachClicklistener.onStomachClick(medicine)
            detailClickListener.onMedicineDetailClick()
        }
        with(binding) {
            ivStomachImage.setImageResource(R.drawable.stomach)
            tvStomachMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
            tvPharmacyNumber.text = medicine.itemSeq
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            stomachClicklistener: StomachItemClickListener,
            detailClickListener: MedicineDetailClickListener
        ): StomachItemViewHolder {

            val binding = ItemStomachListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return StomachItemViewHolder(binding, stomachClicklistener, detailClickListener)
        }
    }
}
