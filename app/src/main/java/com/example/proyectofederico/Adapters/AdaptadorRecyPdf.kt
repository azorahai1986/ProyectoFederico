package com.example.proyectofederico.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofederico.ModelosDeDatos.ProductosSeleccionados
import com.example.proyectofederico.R
import com.example.proyectofederico.ViewHolders.ActPdfViewHolder

class AdaptadorRecyPdf(var datos: ArrayList<ProductosSeleccionados>): RecyclerView.Adapter<ActPdfViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActPdfViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.teplate_recycler_pdf, parent, false)

        return ActPdfViewHolder(itemView)

    }

    override fun getItemCount() = datos.size


    override fun onBindViewHolder(holder: ActPdfViewHolder, position: Int) {
        val dat = datos[position]
        holder.articulo.text = dat.producto
        holder.cantidad.text = dat.cantidad.toString()
        holder.precioUnidad.text = dat.precio.toString()
        holder.precioTotal.text = dat.precioTotal.toString()
    }
}