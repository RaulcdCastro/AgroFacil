package com.example.agrofacil

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class RegisterHarvestActivity : AppCompatActivity() {

    private var cultureId: Int = -1
    private lateinit var tvCultureName: TextView
    private lateinit var etQuantity: EditText
    private lateinit var etHarvestDate: EditText
    private lateinit var etNotes: EditText
    private lateinit var btnSaveHarvest: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_harvest)

        tvCultureName = findViewById(R.id.tvCultureName)
        etQuantity = findViewById(R.id.etQuantity)
        etHarvestDate = findViewById(R.id.etHarvestDate)
        etNotes = findViewById(R.id.etNotes)
        btnSaveHarvest = findViewById(R.id.btnSaveHarvest)

        // Novo botão de voltar
        val btnBack = findViewById<Button>(R.id.btnBackHarvest)
        btnBack.setOnClickListener {
            finish()
        }

        cultureId = intent.getIntExtra("cultureId", -1)
        val cultureName = intent.getStringExtra("cultureName") ?: "Cultura"

        tvCultureName.text = cultureName

        btnSaveHarvest.setOnClickListener {
            val quantityText = etQuantity.text.toString().trim()
            val harvestDate = etHarvestDate.text.toString().trim()

            if (quantityText.isEmpty() || harvestDate.isEmpty()) {
                Toast.makeText(this, "Informe quantidade e data da colheita", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantity = quantityText.toDoubleOrNull()
            if (quantity == null || quantity <= 0) {
                Toast.makeText(this, "Informe uma quantidade válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultIntent = Intent().apply {
                putExtra("cultureId", cultureId)
                putExtra("quantity", quantity)
                putExtra("harvestDate", harvestDate)
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
