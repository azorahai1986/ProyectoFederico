package com.example.proyectofederico.ModelosDeDatos

import java.io.Serializable

data class ProductosSeleccionados(var producto: String = "DEFAULT NAME",
                                  var precio: Double,
                                  var cantidad: Int,
                                  var precioTotal: Double): Serializable {
    constructor():this("",0.0,0,0.0)
}
