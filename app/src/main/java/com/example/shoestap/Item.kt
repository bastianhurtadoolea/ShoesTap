package com.example.shoestap

import java.io.Serializable

data class Item(
    val nombre: String,
    val urlImagen: String,
    val precio: Double
) : Serializable