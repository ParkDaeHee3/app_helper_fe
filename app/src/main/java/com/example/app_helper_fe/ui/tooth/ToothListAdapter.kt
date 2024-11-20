package com.example.app_helper_fe.ui.tooth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.R
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemToothListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener

class ToothListAdapter(
    private val items: List<Medicine.Body.Item>,
    private val toothClicklistener: ToothItemClickListener,
    private val detailClickListener: MedicineDetailClickListener
) : RecyclerView.Adapter<ToothItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ToothItemViewHolder {
        return ToothItemViewHolder.from(parent, toothClicklistener, detailClickListener)
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
    private val toothClicklistener: ToothItemClickListener,
    private val detailClickListener: MedicineDetailClickListener

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine.Body.Item) {
        itemView.setOnClickListener {
            toothClicklistener.onToothClick(medicine)
            detailClickListener.onMedicineDetailClick()

        }
        with(binding) {
            ivToothImage.setImageResource(R.drawable.tooth)
            tvToothMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
            tvPharmacyNumber.text = medicine.itemSeq
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            toothClicklistener: ToothItemClickListener,
            detailClickListener: MedicineDetailClickListener

        ): ToothItemViewHolder {

            val binding = ItemToothListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ToothItemViewHolder(binding, toothClicklistener, detailClickListener)
        }
    }
}
