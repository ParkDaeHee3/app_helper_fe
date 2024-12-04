package com.example.app_helper_fe.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.R
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemAllMedicineBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener
class SearchMedicineListAdapter(
    private val items: List<Medicine>,
    private val searchmedicineClicklistener: SearchMedicineItemClickListener,
    private val detailClickListener: MedicineDetailClickListener

) : RecyclerView.Adapter<SearchMedicineItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchMedicineItemViewHolder {
        return SearchMedicineItemViewHolder.from(
            parent,
            searchmedicineClicklistener,
            detailClickListener
        )
    }

    override fun onBindViewHolder(holder: SearchMedicineItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class SearchMedicineItemViewHolder(
    private val binding: ItemAllMedicineBinding,
    private val searchmedicinelistener: SearchMedicineItemClickListener,
    private val detailClickListener: MedicineDetailClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine) {
        itemView.setOnClickListener {
            searchmedicinelistener.onSearchMedicineClick(medicine)
            detailClickListener.onMedicineDetailClick()
        }
        with(binding) {
            ivAllMedicineImage.setImageResource(R.drawable.cold)
            tvMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
            tvPharmacyNumber.text = medicine.id.toString()
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            searchmedicineClicklistener: SearchMedicineItemClickListener,
            detailClickListener: MedicineDetailClickListener
        ): SearchMedicineItemViewHolder {

            val binding = ItemAllMedicineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return SearchMedicineItemViewHolder(
                binding,
                searchmedicineClicklistener,
                detailClickListener
            )
        }
    }
}
