package com.example.proyectofederico.Actividades

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofederico.*
import com.example.proyectofederico.Adapters.ImagenesAdapter
import com.example.proyectofederico.ModelosDeDatos.Productos
import com.example.proyectofederico.R
import kotlinx.android.synthetic.main.activity_actividad_imagenes.*

class ActividadImagenes : AppCompatActivity() {




    lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_imagenes)

        val producto = intent.getSerializableExtra("producto") as? Productos
        Log.e("PRODUCTO",producto.toString())


        var imagenes = producto!!.imagenes
        Log.e("CANT",imagenes.size.toString())
        //adapter = ViewPagerAdapter(myViews, this,producto)


    }
}

