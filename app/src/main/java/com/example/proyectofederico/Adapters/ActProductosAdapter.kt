package com.example.proyectofederico.Adapters

import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.proyectofederico.R
import com.example.proyectofederico.ViewHolders.ActProductosViewHolder
import kotlinx.android.synthetic.main.template_vista_productos.view.*


class ActProductosAdapter(var imagenesProducto: MutableList<String>, val activity: FragmentActivity): RecyclerView.Adapter<ActProductosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActProductosViewHolder =
        ActProductosViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.template_vista_productos, parent, false))

    override fun getItemCount(): Int = imagenesProducto.size

    override fun onBindViewHolder(holder: ActProductosViewHolder, position: Int) {
        Glide.with(activity).load(imagenesProducto[position]).into(holder.itemView.image_productos)
        //.circleCrop() en Glide esto se usa para que la imagen sea circular, ya no es necesario otra view
    }
}