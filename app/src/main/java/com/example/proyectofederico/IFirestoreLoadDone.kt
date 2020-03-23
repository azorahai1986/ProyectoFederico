package com.example.proyectofederico

import com.example.proyectofederico.ModelosDeDatos.Productos

interface IFirestoreLoadDone {

    fun onImagenLoadSuccess (imagenList: List<Productos>)
    fun onImagenLoadFailed (message: String)
}