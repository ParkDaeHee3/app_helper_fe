package com.example.app_helper_fe.ui.headache

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Headache
import com.example.app_helper_fe.databinding.ItemHeadacheListBinding


class HeadacheListAdapter(
    private val items: List<Headache>,
    private val listener: HeadacheItemClickListener
) : RecyclerView.Adapter<HeadacheItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeadacheItemViewHolder {
        return HeadacheItemViewHolder.from(parent,listener)
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
    private val listener: HeadacheItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(headache: Headache) {
        itemView.setOnClickListener {
            listener.onHeadacheClick(headache)
        }
        with(binding) {
            ivHeadacheImage.setImageResource(headache.medicineResourceId)
            tvHeadacheMedicineName.text = headache.medicineName
            tvPharmacyName.text = headache.pharmacyName
            tvPharmacyNumber.text = headache.pharmacyNumber
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: HeadacheItemClickListener): HeadacheItemViewHolder {
            return HeadacheItemViewHolder(
                ItemHeadacheListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
        }
    }
}
