package com.example.app_helper_fe.ui.muscle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.R
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemMuscleListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener

class MuscleListAdapter(
    private val items: List<Medicine>,
    private val muscleClicklistener: MuscleItemClickListener,
    private val detailClickListener: MedicineDetailClickListener
) : RecyclerView.Adapter<MuscleItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MuscleItemViewHolder {
        return MuscleItemViewHolder.from(parent, muscleClicklistener, detailClickListener)
    }

    override fun onBindViewHolder(holder: MuscleItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class MuscleItemViewHolder(
    private val binding: ItemMuscleListBinding,
    private val muscleClicklistener: MuscleItemClickListener,
    private val detailClickListener: MedicineDetailClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine) {
        itemView.setOnClickListener {
            muscleClicklistener.onMuscleClick(medicine)
            detailClickListener.onMedicineDetailClick()
        }
        with(binding) {
            ivMuscleImage.setImageResource(R.drawable.muscle)
            tvMuscleMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
            tvPharmacyNumber.text = medicine.id.toString()
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            muscleClicklistener: MuscleItemClickListener,
            detailClickListener: MedicineDetailClickListener
        ): MuscleItemViewHolder {

            val binding = ItemMuscleListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return MuscleItemViewHolder(binding, muscleClicklistener, detailClickListener)
        }
    }
}
