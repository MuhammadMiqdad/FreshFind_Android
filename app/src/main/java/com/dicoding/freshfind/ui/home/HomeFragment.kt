package com.dicoding.freshfind.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.freshfind.databinding.FragmentHomeBinding
import com.dicoding.freshfind.ui.CameraXActivity
import com.dicoding.freshfind.ui.adapters.HomePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Setup Camera Icon Click Listener
        binding.cameraIcon.setOnClickListener {
            val intent = Intent(requireContext(), CameraXActivity::class.java)
            startActivity(intent)
        }

        // Setup ViewPager2 and TabLayout
        setupViewPagerAndTabs()

        return root
    }

    private fun setupViewPagerAndTabs() {
        // Set adapter for ViewPager2
        val adapter = HomePagerAdapter(this)
        binding.viewPager.adapter = adapter

        // Connect TabLayout with ViewPager2 using TabLayoutMediator
        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Fish"
                1 -> "Fruits"
                2 -> "Veggies"
                else -> null
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
