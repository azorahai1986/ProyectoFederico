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

    val myViews : Array<Int> = arrayOf(R.layout.layout1,
        R.layout.layout2,
        R.layout.layout3)



    lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_imagenes)

        val producto = intent.getSerializableExtra("producto") as? Productos
        Log.e("PRODUCTO",producto.toString())


        var imagenes = arrayListOf(producto!!.imagen, producto.imagen2)
        Log.e("CANT",imagenes.size.toString())
        //adapter = ViewPagerAdapter(myViews, this,producto)
        viewPager2_imagenes.adapter = ImagenesAdapter(imagenes, this)


    }
}

