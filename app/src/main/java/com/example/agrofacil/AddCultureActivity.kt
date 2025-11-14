package com.example.agrofacil

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCultureActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etType: EditText
    private lateinit var etArea: EditText
    private lateinit var etPlantingDate: EditText
    private lateinit var etHarvestDate: EditText
    private lateinit var etNotes: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_culture)

        etName = findViewById(R.id.etName)
        etType = findViewById(R.id.etType)
        etArea = findViewById(R.id.etArea)
        etPlantingDate = findViewById(R.id.etPlantingDate)
        etHarvestDate = findViewById(R.id.etHarvestDate)
        etNotes = findViewById(R.id.etNotes)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val type = etType.text.toString().trim()
            val areaText = etArea.text.toString().trim()
            val plantingDate = etPlantingDate.text.toString().trim()
            val harvestDate = etHarvestDate.text.toString().trim()
            val notes = etNotes.text.toString().trim()

            if (name.isEmpty() || type.isEmpty() || areaText.isEmpty()
                || plantingDate.isEmpty() || harvestDate.isEmpty()
            ) {
                Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val area = areaText.toDoubleOrNull()
            if (area == null) {
                Toast.makeText(this, "Informe uma área válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultIntent = Intent().apply {
                putExtra("name", name)
                putExtra("type", type)
                putExtra("area", area)
                putExtra("plantingDate", plantingDate)
                putExtra("harvestDate", harvestDate)
                putExtra("notes", notes)
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
