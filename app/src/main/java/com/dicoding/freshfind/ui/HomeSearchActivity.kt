package com.dicoding.freshfind.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.freshfind.R
import com.dicoding.freshfind.databinding.ActivityHomeSearchBinding
import java.io.File

class HomeSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val imagePath = intent.getStringExtra("image_path")
        if (imagePath != null) {
            val imgFile = File(imagePath)
            if (imgFile.exists()) {
                // Tampilkan gambar di ImageView
                val bitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                binding.capturedImageView.setImageBitmap(bitmap)
            } else {
                Toast.makeText(this, "Image file not found", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Image path not received", Toast.LENGTH_SHORT).show()
        }

        // Tambahkan aksi untuk analyzeButton
        binding.analyzeButton.setOnClickListener {
            Toast.makeText(this, "Analyzing image...", Toast.LENGTH_SHORT).show()
            // Tambahkan logika untuk menganalisis gambar
        }
    }
}