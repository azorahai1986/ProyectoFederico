package com.example.proyectofederico.domain.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyectofederico.Productos
import com.google.firebase.firestore.FirebaseFirestore

class Repo {
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
}