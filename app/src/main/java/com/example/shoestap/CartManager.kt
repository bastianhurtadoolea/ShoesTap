package com.example.shoestap

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CartManager {
    private const val PREF_NAME = "CartPrefs"
    private const val KEY_CART = "cart_items"

    // Leer lo que hay en el carrito
    fun getCart(context: Context): MutableList<Item> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_CART, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Item>>() {}.type
        return Gson().fromJson(json, type)
    }

    // Agregar una zapatilla
    fun addItem(context: Context, item: Item) {
        val currentCart = getCart(context)
        currentCart.add(item)
        saveCart(context, currentCart)
    }

    // Borrar una zapatilla específica
    fun removeItem(context: Context, item: Item) {
        val currentCart = getCart(context)
        currentCart.remove(item)
        saveCart(context, currentCart)
    }

    // Vaciar todo el carrito
    fun clearCart(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_CART).apply()
    }

    // Función interna para guardar
    private fun saveCart(context: Context, cart: List<Item>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(cart)
        prefs.edit().putString(KEY_CART, json).apply()
    }
}