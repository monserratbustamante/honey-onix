package com.example.honeyonix


import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.activity.ComponentActivity
import com.example.honeyonix.databinding.ActivityCatalogoBinding
import android.content.Intent

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

public class CatalogoActivity : ComponentActivity() {

    private lateinit var binding: ActivityCatalogoBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar el binding
        binding = ActivityCatalogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Configurar el PopupMenu después de que se haya establecido el contenido de la vista
        binding.Menu.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.popup, popupMenu.menu)

            // Configurar el listener para los ítems del menú
            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.menu_Registrar -> {
                        // Acción para Opción 1: Ir a la primera actividad
                        startActivity(Intent(this, AgregarProductoActivity::class.java))
                        true
                    }
                    R.id.menu_Cuenta -> {
                        startActivity(Intent(this, PerfilAdminActivity::class.java))
                        true                    }
                    else -> false
                }
            }

            popupMenu.show()
        }

        // Configurar los click listeners para otros elementos
        binding.Carrito.setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
            finish()
        }

        binding.Explorar.setOnClickListener {
            startActivity(Intent(this, CatalogoActivity::class.java))
            finish()
        }
    }

    private fun logout() {
        // Cerrar sesión de Google
        googleSignInClient.signOut().addOnCompleteListener(this) {
            // Obtener SharedPreferences de la aplicación
            val sharedPref = getSharedPreferences("myPrefs", MODE_PRIVATE)
            sharedPref?.edit()?.clear()?.apply()

            // Redirigir al usuario a la pantalla de inicio de sesión
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
