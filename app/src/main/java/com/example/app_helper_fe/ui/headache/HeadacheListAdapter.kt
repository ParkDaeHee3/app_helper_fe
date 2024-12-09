package com.example.app_helper_fe.ui.headache

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
            detailClickListener.onMedicineDetailClick(medicine.id)
        }
        with(binding) {
            // Base64 문자열 (예시)
            val base64Image = medicine.image;

            // Base64 문자열을 Bitmap으로 디코딩
            val bitmap = decodeBase64ToBitmap(base64Image)

            bitmap?.let {
                binding.ivHeadacheImage.setImageBitmap(it)
            }
            tvHeadacheMedicineName.text = medicine.itemName
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
