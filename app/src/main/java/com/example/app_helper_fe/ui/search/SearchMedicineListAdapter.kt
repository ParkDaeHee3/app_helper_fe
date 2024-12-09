package com.example.app_helper_fe.ui.search

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
            detailClickListener.onMedicineDetailClick(medicine.id)
        }
        with(binding) {

            // Base64 문자열 (예시)
            val base64Image = medicine.image;

            // Base64 문자열을 Bitmap으로 디코딩
            val bitmap = decodeBase64ToBitmap(base64Image)

            bitmap?.let {
                binding.ivAllMedicineImage.setImageBitmap(it)
            }
            tvMedicineName.text = medicine.itemName
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
