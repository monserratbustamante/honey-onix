package com.example.honeyonix

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.honeyonix.databinding.ActivityCambioPassBinding
import com.google.firebase.auth.FirebaseAuth

class CambioPassActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityCambioPassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambioPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Recuperar Contraseña"
        binding.regresar1.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Handle Reset Password Button click
        binding.RecuperarBtn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            if (email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Correo de recuperación enviado.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "No se pudo enviar el correo de recuperación.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, ingrese su correo electrónico.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
