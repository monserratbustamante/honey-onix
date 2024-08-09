package com.example.honeyonix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.honeyonix.CambioPassActivity
import com.example.honeyonix.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PerfilAdminActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var adminEmail: TextView
    private lateinit var btnEditAdmin: Button
    private lateinit var btnChangePassword: Button
    private lateinit var btnLogout: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_admin)

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Link UI elements
        adminEmail = findViewById(R.id.adminEmail)
        btnEditAdmin = findViewById(R.id.btnEditAdmin)
        btnChangePassword = findViewById(R.id.btnChangePassword)
        btnLogout = findViewById(R.id.btnLogout)
        backButton = findViewById(R.id.regresar)

        // Load admin data
        loadAdminData()

        // Set up button actions
        btnEditAdmin.setOnClickListener {
            startActivity(Intent(this, RegistroAdminActivity::class.java))
        }

        btnChangePassword.setOnClickListener {
            startActivity(Intent(this, CambioPassActivity::class.java))
        }

        btnLogout.setOnClickListener {
            signOut()
        }

        backButton.setOnClickListener {
            finish() // Go back to the previous activity
        }
    }

    private fun loadAdminData() {
        val user = mAuth.currentUser
        user?.let {
            db.collection("administradores")
                .document(it.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {

                        val email = document.getString("email") ?: "Email no disponible"


                        adminEmail.text = email
                        // Load profile image if exists, you can use an image loading library like Glide or Picasso
                    } else {
                        Toast.makeText(this, "No se pudieron cargar los datos del administrador", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al cargar los datos: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun signOut() {
        mAuth.signOut()
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}