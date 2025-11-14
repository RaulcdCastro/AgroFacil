package com.example.agrofacil

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agrofacil.model.Tip
import com.example.agrofacil.ui.TipAdapter

class BoasPraticasActivity : AppCompatActivity() {

    private lateinit var rvTips: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boas_praticas)

        rvTips = findViewById(R.id.rvTips)

        // Botão voltar
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Fecha esta tela e volta para a MainActivity
        }

        val tips = listOf(
            Tip(
                title = "Regue nos horários mais frescos",
                description = "Priorize a irrigação no início da manhã ou no fim da tarde para reduzir perdas por evaporação e evitar choque térmico nas plantas.",
                category = "Irrigação"
            ),
            Tip(
                title = "Evite encharcamento do solo",
                description = "Solo encharcado reduz o oxigênio disponível para as raízes e favorece doenças fúngicas. Ajuste a lâmina de água conforme o tipo de cultura.",
                category = "Irrigação"
            ),
            Tip(
                title = "Use adubação equilibrada",
                description = "Combine adubação orgânica e mineral, sempre respeitando recomendações técnicas, para manter o solo fértil sem excesso de nutrientes.",
                category = "Adubação"
            ),
            Tip(
                title = "Faça rotação de culturas",
                description = "Alterne culturas de raízes, grãos e hortaliças para quebrar ciclos de pragas e doenças e melhorar a estrutura do solo.",
                category = "Rotação de culturas"
            ),
            Tip(
                title = "Proteja o solo com cobertura",
                description = "Use palhada, plantas de cobertura ou restos vegetais para reduzir erosão, manter umidade e aumentar a matéria orgânica do solo.",
                category = "Manejo do solo"
            ),
            Tip(
                title = "Monitore pragas com frequência",
                description = "Caminhe pela lavoura com regularidade para identificar ataques no início e poder agir com medidas de controle mais simples e baratas.",
                category = "Manejo sustentável"
            )
        )

        rvTips.layoutManager = LinearLayoutManager(this)
        rvTips.adapter = TipAdapter(tips)
    }
}
