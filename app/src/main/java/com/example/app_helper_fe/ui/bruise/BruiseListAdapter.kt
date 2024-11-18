package com.example.app_helper_fe.ui.bruise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Bruise
import com.example.app_helper_fe.databinding.ItemBruiseListBinding


class BruiseListAdapter(
    private val items: List<Bruise>,
    private val listener: BruiseItemClickListener
) : RecyclerView.Adapter<BruiseItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BruiseItemViewHolder {
        return BruiseItemViewHolder.from(parent,listener)
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
    private val listener: BruiseItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bruise:Bruise) {
        itemView.setOnClickListener {
            listener.onBruiseClick(bruise)
        }
        with(binding) {
            ivBruiseImage.setImageResource(bruise.medicineResourceId)
            tvBruiseMedicineName.text = bruise.medicineName
            tvPharmacyName.text = bruise.pharmacyName
            tvPharmacyNumber.text = bruise.pharmacyNumber
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: BruiseItemClickListener): BruiseItemViewHolder {
            return BruiseItemViewHolder(
                ItemBruiseListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
        }
    }
}
