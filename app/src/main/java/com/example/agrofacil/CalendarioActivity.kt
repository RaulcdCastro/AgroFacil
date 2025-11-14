package com.example.agrofacil

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agrofacil.model.Culture
import com.example.agrofacil.ui.CalendarAdapter

class CalendarioActivity : AppCompatActivity() {

    private lateinit var rvCalendar: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

        rvCalendar = findViewById(R.id.rvCalendar)
        val btnBack = findViewById<Button>(R.id.btnBackCalendar)

        btnBack.setOnClickListener {
            finish()
        }

        @Suppress("UNCHECKED_CAST")
        val cultures =
            intent.getSerializableExtra("cultures") as? ArrayList<Culture> ?: arrayListOf()

        rvCalendar.layoutManager = LinearLayoutManager(this)
        rvCalendar.adapter = CalendarAdapter(cultures)
    }
}
