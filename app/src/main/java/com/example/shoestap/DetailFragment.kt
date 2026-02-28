package com.example.shoestap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController // <-- ¡Míralo aquí arriba, con sus amigos los imports!
import com.bumptech.glide.Glide
import com.example.shoestap.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuramos la flecha manual de volver atrás
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // 1. Recibimos el objeto mediante Bundle (como pide la rúbrica)
        val zapatilla = arguments?.getSerializable("zapatilla") as? Item

        zapatilla?.let { item ->
            // 2. Llenamos los textos
            binding.tvDetailName.text = item.nombre
            binding.tvDetailPrice.text = "$${item.precio.toInt()}"

            // 3. Cargamos la imagen con Glide
            Glide.with(this)
                .load(item.urlImagen)
                .into(binding.ivDetailImage)

            // 4. Configuramos el botón para que guarde en SharedPreferences
            binding.btnAddCart.setOnClickListener {
                CartManager.addItem(requireContext(), item)
                Toast.makeText(requireContext(), "${item.nombre} agregado al carrito 🛒", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}