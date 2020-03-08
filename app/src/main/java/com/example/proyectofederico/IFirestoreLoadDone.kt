package com.example.proyectofederico

interface IFirestoreLoadDone {

    fun onImagenLoadSuccess (imagenList: List<Productos>)
    fun onImagenLoadFailed (message: String)
}