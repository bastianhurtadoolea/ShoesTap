package com.example.shoestap

object ShoeProvider {

    fun returnShoeList(): List<Item> {
        val shoeList = mutableListOf<Item>()

        shoeList.add(Item("Nike Air Max", "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=500&q=80", 89990.0))
        shoeList.add(Item("Converse All Star", "https://images.unsplash.com/photo-1491553895911-0055eca6402d?w=500&q=80", 49990.0))
        shoeList.add(Item("Vans Old Skool", "https://images.unsplash.com/photo-1525966222134-fcfa99b8ae77?w=500&q=80", 54990.0))
        shoeList.add(Item("Adidas Yeezy", "https://images.unsplash.com/photo-1608231387042-66d1773070a5?w=500&q=80", 189990.0))
        shoeList.add(Item("Nike Air Jordan", "https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?w=500&q=80", 129990.0))
        shoeList.add(Item("New Balance 574", "https://images.unsplash.com/photo-1539185441755-769473a23570?w=500&q=80", 69990.0))
        shoeList.add(Item("Puma RS-X", "https://images.unsplash.com/photo-1606107557195-0e29a4b5b4aa?w=500&q=80", 79990.0))
        shoeList.add(Item("Reebok Classic", "https://images.unsplash.com/photo-1551107696-a4b0c5a0d9a2?w=500&q=80", 64990.0))
        shoeList.add(Item("Timberland Boots", "https://images.unsplash.com/photo-1520639888713-7851133b1ed0?w=500&q=80", 149990.0))
        shoeList.add(Item("Adidas Superstar", "https://images.unsplash.com/photo-1560769629-975ec94e6a86?w=500&q=80", 74990.0))

        return shoeList
    }
}