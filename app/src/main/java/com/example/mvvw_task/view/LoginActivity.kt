package com.example.crud_assignments.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.crud_assignments.databinding.ActivityLoginBinding
import com.example.crud_assignments.usermodel
import com.example.crud_assignments.viewmodel.UserViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize ViewModel
        val factory = usermodel()
        viewModel = usermodel(this, factory).get(UserViewModel::class.java)

        // Setup onClickListener for SignUp button
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        // Setup onClickListener for SignIn button
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signInUser(email, password) { success, message ->
                    if (success) {
                        startActivity(Intent(this, DashboardActivity::class.java))
                        finish()
                    } else {
                        // Show error message to user if sign-in fails
                        // You can also display this in a TextView or Toast
                    }
                }
            } else {
                // Handle case where email or password is empty
            }
        }
    }
}
