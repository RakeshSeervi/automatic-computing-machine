package com.example.MAD.Auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.MAD.databinding.FragmentCardBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AuthCard.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthCard : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = "Card"

        val binding = FragmentCardBinding.inflate(inflater, container, false)
        val args = arguments?.let {
            AuthCardArgs.fromBundle(requireArguments())
        }
        if (args != null)
            binding.displayName.text = AuthCardArgs.fromBundle(requireArguments()).username
        return binding.root
    }
}