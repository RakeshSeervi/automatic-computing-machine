package com.example.MAD.Auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.MAD.databinding.FragmentSignUpBinding
import java.util.regex.Pattern

/**
 * A simple [Fragment] subclass.
 * Use the [SignUp.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUp : Fragment() {
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = "Sign Up"
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.login.setOnClickListener {
            findNavController().navigate(
                SignUpDirections.actionSignUpFragmentToLoginFragment(
                    binding.username.text.toString(), binding.password.text.toString()
                )
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val pattern =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$)..{8,}\$")
        var flag1 = false
        var flag2 = false

        val afterUsernameChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                flag1 = usernameEditText.text.isNotEmpty()
                usernameEditText.error = if (!flag1) "username cannot be empty" else null
                binding.login.isEnabled = flag1 && flag2
            }
        }

        val afterPasswordChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                flag2 = pattern.matcher(passwordEditText.text).matches()
                passwordEditText.error =
                    if (!flag2) "Password should be minimum 8 characters long, should contain at least one capital letter, at least one small letter, at least one number, at least one special character and should not contain whitespaces" else null
                binding.login.isEnabled = flag1 && flag2
            }
        }
        usernameEditText.addTextChangedListener(afterUsernameChangedListener)
        passwordEditText.addTextChangedListener(afterPasswordChangedListener)
    }
}