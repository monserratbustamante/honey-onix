package com.example.honeyonix

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.honeyonix.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Login"

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        binding.registerTV.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding.forgotPasswordTV.setOnClickListener {
            startActivity(Intent(this, CambioPassActivity::class.java))
            finish()
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.emailLogin.text.toString()
            val password = binding.passwordLogin.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Verificar si el usuario es un administrador en la base de datos
                        val userId = auth.currentUser?.uid
                        if (userId != null) {
                            database.child("users").child(userId).child("Administrador").get().addOnSuccessListener { dataSnapshot ->
                                val isAdmin = dataSnapshot.getValue(Boolean::class.java) ?: false
                                if (isAdmin) {
                                    // Redirigir a la actividad de administrador
                                    startActivity(Intent(this, CatalogoActivity::class.java))
                                } else {
                                    // Redirigir a la actividad de cliente
                                    startActivity(Intent(this, CatalogoClienteActivity::class.java))
                                }
                                finish() // Finalizar la actividad de inicio de sesión
                            }.addOnFailureListener {
                                Toast.makeText(this, "Error al verificar rol: ${it.localizedMessage}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}