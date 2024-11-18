package com.example.app_helper_fe.ui.muscle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Muscle
import com.example.app_helper_fe.databinding.ItemMuscleListBinding


class MuscleListAdapter(
    private val items: List<Muscle>,
    private val listener: MuscleItemClickListener
) : RecyclerView.Adapter<MuscleItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MuscleItemViewHolder {
        return MuscleItemViewHolder.from(parent,listener)
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
    private val listener: MuscleItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(muscle: Muscle) {
        itemView.setOnClickListener {
            listener.onMuscleClick(muscle)
        }
        with(binding) {
            ivMuscleImage.setImageResource(muscle.medicineResourceId)
            tvMuscleMedicineName.text = muscle.medicineName
            tvPharmacyName.text = muscle.pharmacyName
            tvPharmacyNumber.text = muscle.pharmacyNumber
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: MuscleItemClickListener): MuscleItemViewHolder {
            return MuscleItemViewHolder(
                ItemMuscleListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
        }
    }
}
