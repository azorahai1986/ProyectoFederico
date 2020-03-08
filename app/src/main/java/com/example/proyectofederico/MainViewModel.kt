package com.example.proyectofederico

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
}