package com.example.shoestap

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {

    override fun loadShoes() {
        // Aquí el Presenter le pide los datos al Modelo
        val shoeList = ShoeProvider.returnShoeList()

        // Y se los entrega a la Vista (El Fragmento)
        view.showShoes(shoeList)
    }
}