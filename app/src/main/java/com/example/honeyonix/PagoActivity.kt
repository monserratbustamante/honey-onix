package com.example.honeyonix

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.honeyonix.databinding.ActivityCarritoBinding
import com.example.honeyonix.databinding.ActivityPagoBinding

class PagoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPagoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}