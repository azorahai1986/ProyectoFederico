package com.example.proyectofederico.ModelosDeDatos

import java.io.Serializable

data class Productos(var imagen: String = "DEFAULT URL",
                     //imagen que se quede, como imagen principal
                var imagenes:ArrayList<String> = arrayListOf(),
                var producto: String = "DEFAULT NAME",
                var precio: String = "DEFAULT DESC"): Serializable

