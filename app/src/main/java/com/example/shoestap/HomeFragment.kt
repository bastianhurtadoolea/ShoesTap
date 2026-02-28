package com.example.shoestap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoestap.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), HomeContract.View {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: HomePresenter
    private lateinit var adapter: ShoeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Instanciamos el Presentador
        presenter = HomePresenter(this)

        // 2. Configuramos el RecyclerView (en grilla de 2 columnas para que se vea como catálogo)
        binding.rvShoes.layoutManager = GridLayoutManager(requireContext(), 2)

        // 3. Le pedimos al presentador que cargue los datos
        presenter.loadShoes()

        // 🚀 NUEVO: Configuramos el clic del botón flotante para ir al Carrito
        binding.fabCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }
    }

    // 4. Esta función la llama el Presentador cuando tiene la lista lista
    override fun showShoes(shoes: List<Item>) {
        adapter = ShoeAdapter(shoes) { zapatillaSeleccionada ->

            // Creamos un paquete (Bundle) con la zapatilla seleccionada (Requisito de la rúbrica)
            val bundle = Bundle().apply {
                putSerializable("zapatilla", zapatillaSeleccionada)
            }

            // Viajamos al detalle llevando el paquete
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
        binding.rvShoes.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}