package com.example.restoapp


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restoapp.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class PhotoAdapter(activity: AppCompatActivity, private val items: List<String>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return items.count()
    }

    override fun createFragment(position: Int): Fragment {
        return Photo.newInstance(
            items[position]
        )
    }
}



class Photo : Fragment() {

    private lateinit var binding: ActivityDetailBinding

            override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            {
                binding = ActivityDetailBinding.inflate(inflater, container, false)
                return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.

        //if(url?.isNotEmpty() == true) {
        //  Picasso.get().load(url).placeholder(R.drawable.).into(binding.photo)
        //}
    }
}