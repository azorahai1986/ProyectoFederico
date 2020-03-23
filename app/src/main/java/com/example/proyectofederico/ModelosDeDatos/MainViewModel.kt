package com.example.proyectofederico.ModelosDeDatos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectofederico.ModelosDeDatos.Carteles
import com.example.proyectofederico.ModelosDeDatos.Productos
import com.example.proyectofederico.domain.data.Repo

class MainViewModel: ViewModel() {
    val repo = Repo()

    fun fetchUserData(): LiveData<MutableList<Productos>> {
        val mutableData = MutableLiveData<MutableList<Productos>>() // crearé una clase llamada repo
        repo.getUserData().observeForever{ userList ->
            mutableData.value = userList
        }
        return mutableData
    }
  // para traer imagenes desde firebase para el cartel principal
    fun fetchUserDataCartel(): LiveData<Carteles> {
        val mutableData = MutableLiveData<Carteles>() // crearé una clase llamada repo
        repo.getUserCartel().observeForever{ cartel ->
            mutableData.value = cartel
        }
        return mutableData
    }
}