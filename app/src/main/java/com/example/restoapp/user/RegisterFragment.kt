package com.example.restoapp.user

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restoapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    var interact: UserActivityFragmentInteraction? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        interact = context as? UserActivityFragmentInteraction
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSignIn.setOnClickListener{
            interact?.showlogin()
        }

        binding.buttonRegisterSubmit.setOnClickListener{
            interact?.makerequest(
                binding.registerEmail.text.toString(),
                binding.registerPassword.text.toString(),
                binding.registerNameInput.text.toString(),
                binding.registerLastNameInput.text.toString(),
                false
            )
        }
    }
}