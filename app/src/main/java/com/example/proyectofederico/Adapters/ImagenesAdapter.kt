package com.example.proyectofederico.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectofederico.R
import com.example.proyectofederico.ViewHolders.ImagenesViewHolder
import kotlinx.android.synthetic.main.custom_layout.view.*
import kotlinx.android.synthetic.main.layout1.view.*

class ImagenesAdapter(var imagenes: ArrayList<String>, val fragmentActivity: FragmentActivity) : RecyclerView.Adapter<ImagenesViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenesViewHolder =
        ImagenesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout1, parent,false)
        )

    override fun getItemCount(): Int = imagenes.size

    override fun onBindViewHolder(holder: ImagenesViewHolder, position: Int) {
        Glide.with(fragmentActivity)
            .load(imagenes[position])
            .into(holder.itemView.image_slide)
    }

}