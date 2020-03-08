package com.example.proyectofederico

import java.io.Serializable

data class Productos(var imagen: String = "DEFAULT URL",
                var imagen2:String = "DEFAULT URL",
                var producto: String = "DEFAULT NAME",
                var precio: String = "DEFAULT DESC"): Serializable
