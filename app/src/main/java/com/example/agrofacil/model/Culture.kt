package com.example.agrofacil.model

import java.io.Serializable

data class Culture(
    val id: Int,
    val name: String,
    val type: String,
    val area: Double,
    val plantingDate: String,
    val harvestDate: String,
    val notes: String = "",

    var harvestedQuantity: Double = 0.0,
    var lastHarvestDate: String = ""
) : Serializable
