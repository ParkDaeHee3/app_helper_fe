package com.example.app_helper_fe.ui.cold

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Cold
import com.example.app_helper_fe.databinding.ItemColdListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener


class ColdListAdapter(
    private val items: List<Cold>,
    private val coldClickListener: ColdItemClickListener,
    private val detailClickListener: MedicineDetailClickListener
) : RecyclerView.Adapter<ColdItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColdItemViewHolder {
        return ColdItemViewHolder.from(parent,coldClickListener,detailClickListener)
    }

    override fun onBindViewHolder(holder: ColdItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ColdItemViewHolder(
    private val binding: ItemColdListBinding,
    private val coldClickListener: ColdItemClickListener,
    private val detailClickListener: MedicineDetailClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cold: Cold) {
        itemView.setOnClickListener {
           coldClickListener.onColdClick(cold)
            detailClickListener.onMedicineDetailClick()

/*
            //activity medicine detail로 이동 하는 기능 (adapter 및 listener를 사용 않고 이동 => 여기 viewholder에서 바로 이동 )
            val action = ColdMedicineFragmentDirections.actionViewCardColdAreaToMedicineDetail()
            it.findNavController().navigate(action)

 */

        }
        with(binding) {
            ivColdImage.setImageResource(cold.medicineResourceId)
            tvColdMedicineName.text = cold.medicineName
            tvPharmacyName.text = cold.pharmacyName
            tvPharmacyNumber.text = cold.pharmacyNumber
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            coldClickListener: ColdItemClickListener,
            detailClickListener: MedicineDetailClickListener
            ): ColdItemViewHolder {

               val binding =  ItemColdListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ColdItemViewHolder( binding,coldClickListener,detailClickListener)

        }
    }
}
