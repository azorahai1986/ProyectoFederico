package com.example.proyectofederico.Actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.proyectofederico.R
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.example.proyectofederico.Adapters.ActProductosAdapter
import com.example.proyectofederico.ModelosDeDatos.MainViewModel
import com.example.proyectofederico.ModelosDeDatos.Productos
import kotlinx.android.synthetic.main.activity_vista_producto.*


class VistaProducto : AppCompatActivity() {

    var viewPagerProducto: ViewPager2? = null
    var adapter: ActProductosAdapter? = null
    private val handler = Handler()
    private val runnable = Runnable {
        if(adapter?.imagenesProducto?.size != 0) {
            viewPagerProducto?.currentItem = if (viewPagerProducto!!.currentItem == adapter!!.imagenesProducto.size-1) 0
            else viewPagerProducto!!.currentItem+1
        }
    }
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_producto)



      //  val productos = mutableListOf<Productos>()
        val producto = intent.getSerializableExtra("producto") as? Productos



        // inflaré las imagenes al viewPager
        viewPagerProducto = findViewById(R.id.imagenVistaProducto)
        adapter = ActProductosAdapter(producto!!.imagenes, this)
        viewPagerProducto?.adapter = adapter

        // le daré el tiempo de scroll automatico
        viewPagerProducto?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 3000)
            }
        })


        val producto1 = findViewById<TextView>(R.id.textView)
        val precio1 = findViewById<TextView>(R.id.textView2)


        if (producto != null) {
            producto1.setText(producto.producto)
            precio1.setText(producto.precio)
        }






    }

}
