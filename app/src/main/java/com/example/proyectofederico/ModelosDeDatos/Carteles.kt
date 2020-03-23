package com.example.proyectofederico.ModelosDeDatos

import java.io.Serializable

class Carteles (var cartel: String = "DEFAULT URL",
                var cartel1:String = "DEFAULT URL",
                var cartel2: String = "DEFAULT URL",
                var cartel3: String = "DEFAULT URL",
                var cartel4: String = "DEFAULT URL"): Serializable{
    fun toArray() = mutableListOf(cartel, cartel1, cartel2, cartel3, cartel4)
}