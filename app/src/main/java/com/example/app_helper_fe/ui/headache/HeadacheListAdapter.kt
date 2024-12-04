package com.example.app_helper_fe.ui.headache

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.R
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemHeadacheListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener

class HeadacheListAdapter(
    private val items: List<Medicine>,
    private val headacheClicklistener: HeadacheItemClickListener,
    private val detailClickListener: MedicineDetailClickListener
) : RecyclerView.Adapter<HeadacheItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeadacheItemViewHolder {
        return HeadacheItemViewHolder.from(parent,headacheClicklistener,detailClickListener)
    }

    override fun onBindViewHolder(holder: HeadacheItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class HeadacheItemViewHolder(
    private val binding: ItemHeadacheListBinding,
    private val headacheClicklistener: HeadacheItemClickListener,
    private val detailClickListener: MedicineDetailClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine) {
        itemView.setOnClickListener {
            headacheClicklistener.onHeadacheClick(medicine)
            headacheClicklistener.onHeadacheClick(medicine)
            detailClickListener.onMedicineDetailClick()
        }
        with(binding) {
            ivHeadacheImage.setImageResource(R.drawable.headache)
            tvHeadacheMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
            tvPharmacyNumber.text = medicine.id.toString()
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            headacheClicklistener: HeadacheItemClickListener,
            detailClickListener: MedicineDetailClickListener
        ): HeadacheItemViewHolder {
                val binding = ItemHeadacheListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return HeadacheItemViewHolder(binding, headacheClicklistener,detailClickListener)
        }
    }
}
