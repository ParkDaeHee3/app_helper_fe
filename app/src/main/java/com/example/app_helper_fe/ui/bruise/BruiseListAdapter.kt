package com.example.app_helper_fe.ui.bruise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.R
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemBruiseListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener


class BruiseListAdapter(
    private val items: List<Medicine>,
    private val bruiseClicklistener: BruiseItemClickListener,
    private val detailClickListener: MedicineDetailClickListener

) : RecyclerView.Adapter<BruiseItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BruiseItemViewHolder {
        return BruiseItemViewHolder.from(parent, bruiseClicklistener, detailClickListener)
    }

    override fun onBindViewHolder(holder: BruiseItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class BruiseItemViewHolder(
    private val binding: ItemBruiseListBinding,
    private val bruiseClicklistener: BruiseItemClickListener,
    private val detailClickListener: MedicineDetailClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine) {
        itemView.setOnClickListener {
            bruiseClicklistener.onBruiseClick(medicine) // click listener를 통해 타박상으로 이동
            detailClickListener.onMedicineDetailClick() // click listener를 통해약품 상세로 이동
        }
        with(binding) {
            ivBruiseImage.setImageResource(R.drawable.muscle)
            tvBruiseMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
            tvPharmacyNumber.text = medicine.id.toString()
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            bruiseClicklistener: BruiseItemClickListener,
            detailClickListener: MedicineDetailClickListener
        ): BruiseItemViewHolder {
            val binding = ItemBruiseListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BruiseItemViewHolder(binding, bruiseClicklistener, detailClickListener)
        }
    }
}
