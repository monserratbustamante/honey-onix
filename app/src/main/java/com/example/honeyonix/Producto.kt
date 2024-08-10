data class Producto(
    var id: String? = null,
    var nombre: String = "",
    var precio: Double = 0.0,
    var descripcion: String = "",
    var cantidad: Int = 0,
    var imagenUrls: List<String> = emptyList() // Cambiamos esto
)