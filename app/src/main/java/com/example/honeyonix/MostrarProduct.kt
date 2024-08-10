package com.example.honeyonix

import Producto
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MostrarProduct : AppCompatActivity() {

    private lateinit var productoAdapter: ProductoAdapter
   // private val productos = Producto.obtenerProductos()  // Asumiendo que tienes un repositorio para obtener los productos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_product)

        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
       // productoAdapter = ProductoAdapter(productos) { producto ->
      //      abrirDetalleProducto(producto)  // Usa la instancia del producto seleccionada
        }

       /* val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productoAdapter*/
    }

    private fun abrirDetalleProducto(producto: Producto) {
    /*    val intent = Intent(this, CatalogoActivity::class.java)
        intent.putExtra("producto", producto)
        startActivity(intent)*/
    }
