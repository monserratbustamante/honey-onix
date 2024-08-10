package com.example.honeyonix
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class RegistroProductActivity : AppCompatActivity() {

    private lateinit var etNombres: EditText
    private lateinit var etPrecio: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var etCantidad: EditText
    private lateinit var btnAgregar: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var imagenAdapter: ImagenAdapter

    private var imagenUris: MutableList<Uri> = mutableListOf()

    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageAgregarP: ImageView

    private companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_product)

        // Inicializando Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("productos")
        storageReference = FirebaseStorage.getInstance().getReference("imagenes")

        etNombres = findViewById(R.id.etNombres)
        etPrecio = findViewById(R.id.etPrecio)
        etDescripcion = findViewById(R.id.etDescripcion)
        etCantidad = findViewById(R.id.etCantidad)
        btnAgregar = findViewById(R.id.btnAgregar)
        imageAgregarP = findViewById(R.id.imageAgregarP)
        recyclerView = findViewById(R.id.RT_ImagenProduct)

        // Configuración del RecyclerView
        imagenAdapter = ImagenAdapter(imagenUris)
        recyclerView.adapter = imagenAdapter

        // Click para seleccionar imágenes
        imageAgregarP.setOnClickListener { abrirGaleria() }

        // Click para agregar producto
        btnAgregar.setOnClickListener { agregarProducto() }
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) // Permitir selección múltiple
        }
        startActivityForResult(Intent.createChooser(intent, "Selecciona imágenes"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.let {
                if (it.clipData != null) { // Si se seleccionan múltiples imágenes
                    val count = it.clipData!!.itemCount
                    for (i in 0 until count) {
                        val uri = it.clipData!!.getItemAt(i).uri
                        imagenUris.add(uri)
                    }
                } else if (it.data != null) { // Si solo se selecciona una imagen
                    val uri = it.data
                    imagenUris.add(uri!!)
                }
                imagenAdapter.notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
            }
        }
    }

    private fun agregarProducto() {
        // Aquí puedes implementar la lógica para guardar el producto en Firebase
    }

}