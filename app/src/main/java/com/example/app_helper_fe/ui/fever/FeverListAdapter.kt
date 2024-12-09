package com.example.app_helper_fe.ui.fever

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemFeverListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener


class FeverListAdapter(
    private val items: List<Medicine>,
    private val feverClicklistener: FeverItemClickListener,
    private val detailClickListener: MedicineDetailClickListener

) : RecyclerView.Adapter<FeverItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeverItemViewHolder {
        return FeverItemViewHolder.from(parent, feverClicklistener, detailClickListener)
    }

    override fun onBindViewHolder(holder: FeverItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class FeverItemViewHolder(
    private val binding: ItemFeverListBinding,
    private val feverClicklistener: FeverItemClickListener,
    private val detailClickListener: MedicineDetailClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine) {
        itemView.setOnClickListener {
            feverClicklistener.onFeverClick(medicine)
            detailClickListener.onMedicineDetailClick(medicine.id)

        }
        with(binding) {
            // Base64 문자열 (예시)
            val base64Image = medicine.image;

            // Base64 문자열을 Bitmap으로 디코딩
            val bitmap = decodeBase64ToBitmap(base64Image)

            bitmap?.let {
                binding.ivFeverImage.setImageBitmap(it)
            }
            tvFeverMedicineName.text = medicine.itemName
            tvPharmacyName.text = medicine.entpName
           // tvPharmacyNumber.text = medicine.id.toString()
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
            feverClicklistener: FeverItemClickListener,
            detailClickListener: MedicineDetailClickListener
        ): FeverItemViewHolder {
            val binding = ItemFeverListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return FeverItemViewHolder(binding, feverClicklistener, detailClickListener)
        }
    }
}
