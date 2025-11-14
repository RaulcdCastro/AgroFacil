package com.example.agrofacil.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agrofacil.R
import com.example.agrofacil.model.Tip

class TipAdapter(
    private val tips: List<Tip>
) : RecyclerView.Adapter<TipAdapter.TipViewHolder>() {

    inner class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCategory: TextView = itemView.findViewById(R.id.tvTipCategory)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTipTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvTipDescription)

        fun bind(tip: Tip) {
            tvCategory.text = tip.category
            tvTitle.text = tip.title
            tvDescription.text = tip.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tip, parent, false)
        return TipViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(tips[position])
    }

    override fun getItemCount(): Int = tips.size
}
