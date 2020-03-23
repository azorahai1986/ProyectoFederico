package com.example.proyectofederico.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.proyectofederico.ModelosDeDatos.Carteles
import com.example.proyectofederico.R
import com.example.proyectofederico.ViewHolders.CartelViewHolder
import kotlinx.android.synthetic.main.item_cartel.view.*

class CartelAdapter(var images: MutableList<String>, val activity: FragmentActivity): RecyclerView.Adapter<CartelViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartelViewHolder =
        CartelViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cartel, parent, false)
        )

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: CartelViewHolder, position: Int) {
        Glide.with(activity)
            .load(images[position])
            .into(holder.itemView.image_item_cartel)
    }

}