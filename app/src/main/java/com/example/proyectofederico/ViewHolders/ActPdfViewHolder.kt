package com.example.proyectofederico.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofederico.R

class ActPdfViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    var articulo = itemView.findViewById<TextView>(R.id.tvArticulo)
    var cantidad = itemView.findViewById<TextView>(R.id.tvCantidad)
    var precioUnidad = itemView.findViewById<TextView>(R.id.tvPrecioUnidad)
    var precioTotal = itemView.findViewById<TextView>(R.id.tvPrecioTotal)

    init {
        this.articulo = articulo
        this.cantidad = cantidad
        this.precioUnidad = precioUnidad
        this.precioTotal = precioTotal
    }
}