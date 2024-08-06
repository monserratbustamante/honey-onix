package com.example.honeyonix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.honeyonix.databinding.ActivityCatalogoBinding
import com.example.honeyonix.databinding.ActivityPerfilAdminBinding

class PerfilAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regresar.setOnClickListener {
            startActivity(Intent(this, CatalogoActivity::class.java))
            finish()
        }
    }
}