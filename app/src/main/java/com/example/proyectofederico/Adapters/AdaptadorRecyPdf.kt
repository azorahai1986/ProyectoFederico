package com.example.proyectofederico.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofederico.R
import com.example.proyectofederico.ViewHolders.ActPdfViewHolder

class AdaptadorRecyPdf(val context: Context): RecyclerView.Adapter<ActPdfViewHolder>() {

    var viewHolder: AdaptadorRecyPdf? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActPdfViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.teplate_recycler_pdf, parent, false)
        viewHolder = AdaptadorRecyPdf(context)

        return ActPdfViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return itemCount
    }


    override fun onBindViewHolder(holder: ActPdfViewHolder, position: Int) {
        val items = ArrayList<String>()
    }
}