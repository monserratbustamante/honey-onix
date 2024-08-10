package com.example.honeyonix

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.activity_catalogo_cliente.*

class CatalogoClienteActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var productoAdapter: ProductoAdapter
    private val productosList = ArrayList<ProductoAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_cliente)

        database = FirebaseDatabase.getInstance().getReference("productos")

        setupRecyclerView()
        obtenerProductosDeFirebase()
    }

    private fun setupRecyclerView() {
      /*  productoAdapter = ProductoAdapter(productosList)
        RecyclerView.layoutManager = LinearLayoutManager(this)
        ProductoAdapter.adapter = productoAdapter*/
    }

    private fun obtenerProductosDeFirebase() {
       // database.addValueEventListener(object : ValueEventListener {
            //override fun onDataChange(snapshot: DataSnapshot) {
                productosList.clear()
              //  for (productoSnapshot in snapshot.children) {
                 // val producto = productoSnapshot.getValue(Producto::class.java)
                  //  if (producto != null) {
                     //   productosList.add(producto)
                    }
                }
               // productoAdapter.notifyDataSetChanged()


          //  override fun onCancelled(error: DatabaseError) {
               // Toast.makeText(this@CatalogoClienteActivity, "Error al obtener los datos", Toast.LENGTH_SHORT).show()
        //    }
     //   })
   // }
//}
