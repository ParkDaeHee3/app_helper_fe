package com.example.app_helper_fe.ui.bruise

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.databinding.ItemBruiseListBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener


class BruiseListAdapter(
    private val items: List<Medicine>,
    private val bruiseClicklistener: BruiseItemClickListener,
    private val detailClickListener: MedicineDetailClickListener

) : RecyclerView.Adapter<BruiseItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BruiseItemViewHolder {
        return BruiseItemViewHolder.from(parent, bruiseClicklistener, detailClickListener)
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
    private val bruiseClicklistener: BruiseItemClickListener,
    private val detailClickListener: MedicineDetailClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine) {
        itemView.setOnClickListener {
            bruiseClicklistener.onBruiseClick(medicine) // click listener를 통해 타박상으로 이동
            detailClickListener.onMedicineDetailClick() // click listener를 통해약품 상세로 이동
        }
        with(binding) {
            // Base64 문자열 (예시)
            val base64Image = medicine.image;

            // Base64 문자열을 Bitmap으로 디코딩
            val bitmap = decodeBase64ToBitmap(base64Image)

            bitmap?.let {
                binding.ivBruiseImage.setImageBitmap(it)
            }
            tvBruiseMedicineName.text = medicine.itemName
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
            bruiseClicklistener: BruiseItemClickListener,
            detailClickListener: MedicineDetailClickListener
        ): BruiseItemViewHolder {
            val binding = ItemBruiseListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BruiseItemViewHolder(binding, bruiseClicklistener, detailClickListener)
        }
    }
}
