package com.dicoding.freshfind.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.freshfind.network.ApiClient
import com.dicoding.freshfind.network.Product
import com.dicoding.freshfind.network.ProductPhoto
import com.dicoding.freshfind.network.ProductWithPhoto
import com.dicoding.freshfind.network.ProductResponse
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _productList = MutableLiveData<List<ProductWithPhoto>>()
    val productList: LiveData<List<ProductWithPhoto>> get() = _productList

    private val apiService = ApiClient.create()

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response: ProductResponse = apiService.getProducts()

                // Cek apakah response berhasil diterima
                Log.d("ProductViewModel", "Data diterima: ${response.productDatas.size} produk ditemukan")

                // Gabungkan produk dengan foto berdasarkan ID
                val productsWithPhotos = mapProductsToWithPhotos(response.productDatas, response.productPhotos)

                // Update LiveData dengan data produk yang sudah digabungkan
                _productList.postValue(productsWithPhotos)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error: ${e.message}")
            }
        }
    }


    private fun mapProductsToWithPhotos(products: List<Product>, photos: List<ProductPhoto>): List<ProductWithPhoto> {
        return products.map { product ->
            val photoUrl = photos.find { it.productId == product.id }?.link
            ProductWithPhoto(product, photoUrl)
        }
    }
}
