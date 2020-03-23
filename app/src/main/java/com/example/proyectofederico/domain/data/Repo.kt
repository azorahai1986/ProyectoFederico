package com.example.proyectofederico.domain.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyectofederico.ModelosDeDatos.Carteles
import com.example.proyectofederico.ModelosDeDatos.Productos
import com.google.firebase.firestore.FirebaseFirestore

class Repo {
    // para el recycler
    fun getUserData(): LiveData<MutableList<Productos>> {
        val mutableData = MutableLiveData<MutableList<Productos>>() // de aca me voy al MainviewModel
        FirebaseFirestore.getInstance().collection("Productos").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Productos>()

            for (document in result){
                val prod = document.toObject(Productos::class.java)
                listData.add(prod)
            }

            mutableData.value = listData

        }
        return mutableData
    }
  // para taer las imagenes desde firebase para el cartel principal
    fun getUserCartel(): LiveData<Carteles> {
        val mutableData = MutableLiveData<Carteles>() // de aca me voy al MainviewModel
        FirebaseFirestore.getInstance().collection("Cartel").document("images")
            .get().addOnSuccessListener { result ->

            mutableData.value = result.toObject(Carteles::class.java)

        }
        return mutableData
    }
}