package com.example.agrofacil
<<<<<<< HEAD
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
=======

import android.content.Intent
import android.os.Bundle
import android.widget.Button
>>>>>>> ea07f2377a24253d5a0c47a5b33106bb771f23e1
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agrofacil.model.Culture
import com.example.agrofacil.ui.CultureAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
<<<<<<< HEAD
=======

>>>>>>> ea07f2377a24253d5a0c47a5b33106bb771f23e1
    private lateinit var rvCultures: RecyclerView
    private lateinit var fabAddCulture: FloatingActionButton
    private lateinit var btnTips: Button
    private lateinit var btnCalendar: Button
<<<<<<< HEAD
    private lateinit var tvEmptyState: TextView
=======
>>>>>>> ea07f2377a24253d5a0c47a5b33106bb771f23e1

    val cultures = mutableListOf<Culture>()
    private lateinit var adapter: CultureAdapter

<<<<<<< HEAD
=======
    // cadastro de cultura
>>>>>>> ea07f2377a24253d5a0c47a5b33106bb771f23e1
    private val addCultureLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        if (result.resultCode == RESULT_OK && data != null) {
            val name = data.getStringExtra("name") ?: return@registerForActivityResult
            val type = data.getStringExtra("type") ?: return@registerForActivityResult
            val area = data.getDoubleExtra("area", 0.0)
            val plantingDate = data.getStringExtra("plantingDate") ?: ""
            val harvestDate = data.getStringExtra("harvestDate") ?: ""
            val notes = data.getStringExtra("notes") ?: ""

            val newId = if (cultures.isEmpty()) 1 else (cultures.maxOf { it.id } + 1)

            val newCulture = Culture(
                id = newId,
                name = name,
                type = type,
                area = area,
                plantingDate = plantingDate,
                harvestDate = harvestDate,
                notes = notes
            )

            cultures.add(newCulture)
            adapter.notifyItemInserted(cultures.size - 1)
<<<<<<< HEAD
            updateEmptyState()
        }
    }


=======
        }
    }

    // registro de produção
>>>>>>> ea07f2377a24253d5a0c47a5b33106bb771f23e1
    private val registerHarvestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        if (result.resultCode == RESULT_OK && data != null) {
            val cultureId = data.getIntExtra("cultureId", -1)
            val quantity = data.getDoubleExtra("quantity", 0.0)
            val harvestDate = data.getStringExtra("harvestDate") ?: ""

            val index = cultures.indexOfFirst { it.id == cultureId }
            if (index != -1 && quantity > 0.0) {
                val culture = cultures[index]
                culture.harvestedQuantity += quantity
                culture.lastHarvestDate = harvestDate
                adapter.notifyItemChanged(index)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCultures = findViewById(R.id.rvCultures)
        fabAddCulture = findViewById(R.id.fabAddCulture)
        btnTips = findViewById(R.id.btnTips)
        btnCalendar = findViewById(R.id.btnCalendar)
<<<<<<< HEAD
        tvEmptyState = findViewById(R.id.tvEmptyState)
=======
>>>>>>> ea07f2377a24253d5a0c47a5b33106bb771f23e1

        adapter = CultureAdapter(cultures) { culture ->
            val intent = Intent(this, RegisterHarvestActivity::class.java).apply {
                putExtra("cultureId", culture.id)
                putExtra("cultureName", culture.name)
            }
            registerHarvestLauncher.launch(intent)
        }

        rvCultures.layoutManager = LinearLayoutManager(this)
        rvCultures.adapter = adapter

<<<<<<< HEAD

=======
>>>>>>> ea07f2377a24253d5a0c47a5b33106bb771f23e1
        fabAddCulture.setOnClickListener {
            val intent = Intent(this, AddCultureActivity::class.java)
            addCultureLauncher.launch(intent)
        }

        btnTips.setOnClickListener {
            val intent = Intent(this, BoasPraticasActivity::class.java)
            startActivity(intent)
        }

<<<<<<< HEAD

        btnCalendar.setOnClickListener {
            val intent = Intent(this, CalendarioActivity::class.java).apply {
                putExtra("cultures", ArrayList(cultures))
            }
            startActivity(intent)
        }


        updateEmptyState()
    }

    private fun updateEmptyState() {
        tvEmptyState.visibility = if (cultures.isEmpty()) View.VISIBLE else View.GONE
=======
        btnCalendar.setOnClickListener {
            val intent = Intent(this, CalendarioActivity::class.java).apply {
                putExtra("cultures", ArrayList(cultures)) // manda a lista atual
            }
            startActivity(intent)
        }
>>>>>>> ea07f2377a24253d5a0c47a5b33106bb771f23e1
    }
}
