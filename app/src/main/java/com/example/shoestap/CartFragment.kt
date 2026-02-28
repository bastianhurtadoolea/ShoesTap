package com.example.shoestap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoestap.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ⬅️ Hacemos que la flecha de volver atrás funcione
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        loadCart() // Cargamos la lista

        // Botón superior para vaciar todo
        binding.btnClearCart.setOnClickListener {
            CartManager.clearCart(requireContext())
            loadCart() // Recargamos para que muestre el texto de "vacío" y el Total en $0
            Toast.makeText(requireContext(), "Carrito vaciado 🗑️", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadCart() {
        val cartItems = CartManager.getCart(requireContext())

        // 💰 Calculamos y mostramos el total inicial
        val total = cartItems.sumOf { it.precio }
        binding.tvTotal.text = "$${total.toInt()}"

        if (cartItems.isEmpty()) {
            binding.tvEmptyCart.visibility = View.VISIBLE
            binding.rvCart.visibility = View.GONE
        } else {
            binding.tvEmptyCart.visibility = View.GONE
            binding.rvCart.visibility = View.VISIBLE

            adapter = CartAdapter(cartItems) { itemToDelete, position ->
                // Acción al tocar el basurero de un producto
                CartManager.removeItem(requireContext(), itemToDelete)
                adapter.removeItem(position)

                // 💰 Recalculamos el total después de borrar una zapatilla
                val newCartItems = CartManager.getCart(requireContext())
                val newTotal = newCartItems.sumOf { it.precio }
                binding.tvTotal.text = "$${newTotal.toInt()}"

                // Si borramos el último, mostramos el texto de vacío
                if (newCartItems.isEmpty()) {
                    binding.tvEmptyCart.visibility = View.VISIBLE
                    binding.rvCart.visibility = View.GONE
                }
            }
            binding.rvCart.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}