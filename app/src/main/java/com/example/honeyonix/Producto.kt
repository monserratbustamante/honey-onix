class Producto {
    // Getters y Setters
    var nombre: String? = null
    var descripcion: String? = null
    var imagenUrl: String? = null
    var precio: Double = 0.0

    constructor()

    constructor(nombre: String?, descripcion: String?, imagenUrl: String?, precio: Double) {
        this.nombre = nombre
        this.descripcion = descripcion
        this.imagenUrl = imagenUrl
        this.precio = precio
    }
}