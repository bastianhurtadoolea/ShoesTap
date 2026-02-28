package com.example.shoestap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoestap.databinding.ItemShoeBinding

class ShoeAdapter(
    private var shoeList: List<Item>,
    private val onClick: (Item) -> Unit // Esto nos servirá luego para hacer clic
) : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    inner class ShoeViewHolder(val binding: ItemShoeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shoe: Item) {
            binding.tvShoeName.text = shoe.nombre
            binding.tvShoePrice.text = "$${shoe.precio.toInt()}"

            // Carga la foto desde el link de internet y la pone en el ImageView
            Glide.with(binding.root.context)
                .load(shoe.urlImagen)
                .into(binding.ivShoe)

            // Configuramos qué pasa si tocan la tarjeta
            binding.root.setOnClickListener {
                onClick(shoe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ItemShoeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    override fun getItemCount(): Int = shoeList.size

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.bind(shoeList[position])
    }

    // Función para actualizar la lista más adelante
    fun updateData(newList: List<Item>) {
        shoeList = newList
        notifyDataSetChanged()
    }
}