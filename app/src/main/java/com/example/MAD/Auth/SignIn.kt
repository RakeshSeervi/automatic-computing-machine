package com.example.MAD.Auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.MAD.databinding.FragmentSignInBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SignIn.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignIn : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    lateinit var username: String
    lateinit var password: String
    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = "Sign In"
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        val args = SignInArgs.fromBundle(requireArguments())
        username = args.username
        password = args.password

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        var flag1 = false
        var flag2 = false

        val afterUsernameChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                flag1 = !usernameEditText.text.isEmpty()
                usernameEditText.error = if (!flag1) "username cannot be empty" else null
                binding.login.isEnabled = flag1 && flag2 && count < 2
            }
        }

        val afterPasswordChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                flag2 = !passwordEditText.text.isEmpty()
                passwordEditText.error = if (!flag2) "password cannot be empty" else null
                binding.login.isEnabled = flag1 && flag2 && count < 2
            }
        }

        usernameEditText.addTextChangedListener(afterUsernameChangedListener)
        passwordEditText.addTextChangedListener(afterPasswordChangedListener)
    }

    fun onClickListener(view: View) {
        if (binding.username.text.toString() == username && binding.password.text.toString() == password) {
            findNavController().navigate(
                SignInDirections.actionLoginFragmentToCard(
                    binding.username.text.toString()
                )
            )
            val appContext = context?.applicationContext
            if (appContext != null)
                Toast.makeText(appContext, "Login successful!", Toast.LENGTH_LONG).show()
        } else {
            count += 1
            if (count == 2) {
                binding.login.isEnabled = false
                val appContext = context?.applicationContext
                if (appContext != null)
                    Toast.makeText(appContext, "Max attempts exceeded", Toast.LENGTH_LONG)
                        .show()
            } else {
                val appContext = context?.applicationContext
                if (appContext != null)
                    Toast.makeText(
                        appContext,
                        "Login failed! One attempt left.",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }
}