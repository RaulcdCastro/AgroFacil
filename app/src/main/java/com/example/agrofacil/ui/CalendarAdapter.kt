package com.example.agrofacil.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agrofacil.R
import com.example.agrofacil.model.Culture
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarAdapter(
    private val cultures: List<Culture>
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
        isLenient = false
    }

    inner class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvCalendarCultureName)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvCalendarStatus)

        fun bind(culture: Culture) {
            tvName.text = culture.name
            tvStatus.text = buildStatusText(culture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(cultures[position])
    }

    override fun getItemCount(): Int = cultures.size

    private fun buildStatusText(culture: Culture): String {
        val planting = parseDateOrNull(culture.plantingDate)
        val harvest = parseDateOrNull(culture.harvestDate)
        val today = Date()

        if (planting == null || harvest == null) {
            return "Datas de plantio/colheita não informadas corretamente."
        }

        return when {
            today.before(planting) -> {
                val daysToPlant = daysBetween(today, planting)
                "Plantio previsto para ${culture.plantingDate} (faltam $daysToPlant dias)."
            }

            !today.after(harvest) -> {
                val daysToHarvest = daysBetween(today, harvest)
                when {
                    daysToHarvest == 0L ->
                        "Hoje é o dia estimado da colheita! Data: ${culture.harvestDate}."
                    daysToHarvest <= 15L ->
                        "Colheita próxima! Faltam $daysToHarvest dias para ${culture.harvestDate}."
                    else ->
                        "Cultura em desenvolvimento. Colheita prevista para ${culture.harvestDate} (faltam $daysToHarvest dias)."
                }
            }

            else -> {
                "Período estimado de colheita (${culture.harvestDate}) já passou. Avalie colher o quanto antes ou encerrar a cultura."
            }
        }
    }

    private fun parseDateOrNull(value: String): Date? {
        return try {
            sdf.parse(value)
        } catch (e: Exception) {
            null
        }
    }

    private fun daysBetween(start: Date, end: Date): Long {
        val diffMillis = end.time - start.time
        return diffMillis / (1000L * 60L * 60L * 24L)
    }
}
