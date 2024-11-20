package com.example.app_helper_fe.ui.cold

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.R
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemColdListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener


class ColdListAdapter(
    private val items: List<Medicine.Body.Item>,
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

    fun bind(medicine: Medicine.Body.Item) {
        itemView.setOnClickListener {
            coldClickListener.onColdClick(medicine)
            //activity medicine detail를 통한
           coldClickListener.onColdClick(medicine)
            detailClickListener.onMedicineDetailClick()

/*
            //activity medicine detail로 이동 하는 기능 (adapter 및 listener를 사용 않고 이동 => 여기 viewholder에서 바로 이동 )
            val action = ColdMedicineFragmentDirections.actionViewCardColdAreaToMedicineDetail()
            it.findNavController().navigate(action)

 */

        }
        with(binding) {
            ivColdImage.setImageResource(R.drawable.cold)
            tvColdMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
            tvPharmacyNumber.text = medicine.itemSeq
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
