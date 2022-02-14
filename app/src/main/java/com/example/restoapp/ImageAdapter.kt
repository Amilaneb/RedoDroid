package com.example.restoapp

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.restoapp.Network.ImageFragment

class ImageAdapter(activity: AppCompatActivity, private val images: List<String>):
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return images.size
    }

    override fun createFragment(position: Int): Fragment {
        return ImageFragment.new(images[position])
    }


}