package com.example.shoestap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoestap.databinding.ItemCartBinding

class CartAdapter(
    private val cartList: MutableList<Item>,
    private val onDeleteClick: (Item, Int) -> Unit // Acción al tocar el basurero
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shoe: Item) {
            binding.tvCartName.text = shoe.nombre
            binding.tvCartPrice.text = "$${shoe.precio.toInt()}"

            Glide.with(binding.root.context)
                .load(shoe.urlImagen)
                .into(binding.ivCartImage)

            // Configuramos el botón de eliminar
            binding.btnDelete.setOnClickListener {
                onDeleteClick(shoe, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position])
    }

    // Función para borrar visualmente el ítem con animación
    fun removeItem(position: Int) {
        cartList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cartList.size)
    }
}