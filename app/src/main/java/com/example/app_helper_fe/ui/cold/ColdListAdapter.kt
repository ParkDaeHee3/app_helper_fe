package com.example.app_helper_fe.ui.cold

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemColdListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener


class ColdListAdapter(
    private val items: List<Medicine>,
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

    fun bind(medicine: Medicine) {
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
            // Base64 문자열 (예시)
            val base64Image = medicine.image;

            // Base64 문자열을 Bitmap으로 디코딩
            val bitmap = decodeBase64ToBitmap(base64Image)

            bitmap?.let {
                binding.ivColdImage.setImageBitmap(it)
            }
            tvColdMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
           // tvPharmacyNumber.text = medicine.id.toString()
        }
    }

    // Base64 문자열을 Bitmap으로 디코딩하는 함수
    private fun decodeBase64ToBitmap(base64String: String): Bitmap? {
        return try {
            // Base64 문자열을 바이트 배열로 디코딩
            val decodedString = Base64.decode(base64String, Base64.DEFAULT)

            // 바이트 배열을 Bitmap으로 디코딩
            BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        } catch (e: Exception) {
            e.printStackTrace()
            null
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
