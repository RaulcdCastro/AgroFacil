package com.example.agrofacil.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agrofacil.R
import com.example.agrofacil.model.Culture

class CultureAdapter(
    private val cultures: List<Culture>,
    private val onItemClick: (Culture) -> Unit
) : RecyclerView.Adapter<CultureAdapter.CultureViewHolder>() {

    inner class CultureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvCultureName)
        private val tvType: TextView = itemView.findViewById(R.id.tvCultureType)
        private val tvDates: TextView = itemView.findViewById(R.id.tvCultureDates)
        private val tvProduction: TextView = itemView.findViewById(R.id.tvProduction)

        fun bind(culture: Culture) {
            tvName.text = culture.name
            tvType.text = "${culture.type} • ${"%.2f".format(culture.area)} ha"
            tvDates.text = "Plantio: ${culture.plantingDate} • Colheita: ${culture.harvestDate}"

            if (culture.harvestedQuantity > 0.0) {
                val baseText =
                    "Produção: ${"%.2f".format(culture.harvestedQuantity)} kg"
                tvProduction.visibility = View.VISIBLE
                tvProduction.text = if (culture.lastHarvestDate.isNotBlank()) {
                    "$baseText (última colheita: ${culture.lastHarvestDate})"
                } else {
                    baseText
                }
            } else {
                tvProduction.visibility = View.GONE
            }

            itemView.setOnClickListener {
                onItemClick(culture)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CultureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_culture, parent, false)
        return CultureViewHolder(view)
    }

    override fun onBindViewHolder(holder: CultureViewHolder, position: Int) {
        holder.bind(cultures[position])
    }

    override fun getItemCount(): Int = cultures.size
}
