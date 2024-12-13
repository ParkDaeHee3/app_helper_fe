package com.example.app_helper_fe.ui.stomach

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemStomachListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener

class StomachListAdapter(
    private val items: List<Medicine>,
    private val stomachClicklistener: StomachItemClickListener,
    private val detailClickListener: MedicineDetailClickListener

) : RecyclerView.Adapter<StomachItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StomachItemViewHolder {
        return StomachItemViewHolder.from(parent, stomachClicklistener, detailClickListener)
    }

    override fun onBindViewHolder(holder: StomachItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class StomachItemViewHolder(
    private val binding: ItemStomachListBinding,
    private val stomachClicklistener: StomachItemClickListener,
    private val detailClickListener: MedicineDetailClickListener

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine) {
        itemView.setOnClickListener {
            stomachClicklistener.onStomachClick(medicine)
            detailClickListener.onMedicineDetailClick(medicine.id)
        }
        with(binding) {

            // Base64 문자열 (예시)
            val base64Image = medicine.image;

            // Base64 문자열을 Bitmap으로 디코딩
            val bitmap = decodeBase64ToBitmap(base64Image)

            bitmap?.let {
                binding.ivStomachImage.setImageBitmap(it)
            }
            tvStomachMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
            //tvPharmacyNumber.text = medicine.id.toString()
        }
    }

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
            stomachClicklistener: StomachItemClickListener,
            detailClickListener: MedicineDetailClickListener
        ): StomachItemViewHolder {

            val binding = ItemStomachListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return StomachItemViewHolder(binding, stomachClicklistener, detailClickListener)
        }
    }
}
