package com.example.restoapp.Network

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.restoapp.ImageAdapter
import com.example.restoapp.databinding.FragmentImageBinding
import com.squareup.picasso.Picasso

class ImageFragment : Fragment() {

    private lateinit var binding : FragmentImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString("url")
        if (url?.isNotEmpty() == true) {
            Picasso.get().load(url).into(binding.imageDish)
        }
    }

    companion object {
        fun new(url: String): ImageFragment {
            return ImageFragment().apply {
                arguments = Bundle().apply {
                    putString("url", url)
                }
            }
        }
    }
}