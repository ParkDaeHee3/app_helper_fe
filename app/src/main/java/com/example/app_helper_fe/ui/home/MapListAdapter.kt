package com.example.app_helper_fe.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MapListAdapter() {

}
  /*
    (
    private val items: List<Transfer>,
    private val listener: MapItemClickListener
) : RecyclerView.Adapter<TransferItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferItemViewHolder {
        return TransferItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TransferItemViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


class TransferItemViewHolder(
    private val binding: ItemTransferHistoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(transfer: Transfer, listener: MapItemClickListener) {
        val account = transfer.account
        itemView.setOnClickListener {
            listener.onTransferClick(transfer)
        }
        with(binding) {
            ivTransferAccountImage.setImageResource(account.profileResourceId)
            tvTransferAccountName.text = account.holderName
            tvTransferDate.text = transfer.sendDate
            tvTransferHistoryAmount.text = itemView.context.getString(
                R.string.format_amount_unit,
                transfer.amount.convertThreeDigitComma()
            )
            tvTransferHistoryBalance.text = itemView.context.getString(
                R.string.format_amount_unit,
                transfer.balance.convertThreeDigitComma()
            )
        }
    }

    companion object {
        fun from(parent: ViewGroup): TransferItemViewHolder {
            return TransferItemViewHolder(
                ItemTransferHistoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}
*/
