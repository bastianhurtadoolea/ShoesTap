package com.example.shoestap

interface HomeContract {
    // Lo que puede hacer la pantalla (Mostrar zapatillas)
    interface View {
        fun showShoes(shoes: List<Item>)
    }
    // Lo que puede hacer el presentador (Cargar zapatillas)
    interface Presenter {
        fun loadShoes()
    }
}