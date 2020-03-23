package com.example.proyectofederico.Actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofederico.R
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.proyectofederico.ModelosDeDatos.Productos
import de.hdodenhof.circleimageview.CircleImageView


class VistaProducto : AppCompatActivity() {

     var tvAcceso: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_producto)

        tvAcceso = findViewById(R.id.tvAcceso)


      //  val productos = mutableListOf<Productos>()
        val producto = intent.getSerializableExtra("producto") as? Productos

        tvAcceso?.setOnClickListener {
            val intent = Intent(this, ActividadImagenes::class.java)
            intent.putExtra("producto",producto)
            startActivity(intent)
        }



        /*val producto1 = findViewById<TextView>(R.id.textView)
        val precio1 = findViewById<TextView>(R.id.textView2)
        val index = intent.getStringExtra("ID")

        producto1.text*/
        val producto1 = findViewById<TextView>(R.id.textView)
        val precio1 = findViewById<TextView>(R.id.textView2)
        val image= findViewById<CircleImageView>(R.id.imagenVistaProducto)

        if (producto != null) {
            producto1.setText(producto.producto)
            precio1.setText(producto.precio)
            Glide.with(applicationContext).load(producto.imagen).into(image)
        }





    }
}
