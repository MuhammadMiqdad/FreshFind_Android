package com.dicoding.freshfind.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.freshfind.databinding.FragmentCategoryBinding
import com.dicoding.freshfind.ui.ProductAdapter
import com.dicoding.freshfind.ui.ProductViewModel

class FishFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        // Set up RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ProductAdapter()
        binding.recyclerView.adapter = adapter

        // Initialize ViewModel
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        // Observe productList
        productViewModel.productList.observe(viewLifecycleOwner, Observer { products ->
            // Filter products by category "ikan"
            val fishProducts = products.filter { it.product.category == "ikan" }
            adapter.submitList(fishProducts)
        })

        // Fetch products
        productViewModel.fetchProducts()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
