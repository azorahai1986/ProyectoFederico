package com.example.proyectofederico.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.template_lista.view.*
import android.widget.NumberPicker
import com.example.proyectofederico.ModelosDeDatos.Productos
import com.example.proyectofederico.R


class AdaptadorRecycler(var dataList: ArrayList<Productos>, private val context: Context, val itemClickListener: OnItemClickListener): RecyclerView.Adapter<AdaptadorRecycler.MainViewHolder>() {



    fun setListData(items: ArrayList<Productos>) {
        dataList = items // con esto cree la lista y la seteo

    }

    //para darle click a mi lista
    var viewHolder: RecyclerView.ViewHolder? = null // 27 cambio el val por var




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.template_lista, parent, false)
        viewHolder = MainViewHolder(itemView)
        //  23. el adapatador en el MainActivity tambien me lo va a pedir.

        return MainViewHolder(itemView)

    }


    override fun getItemCount(): Int {

        return if (dataList.size > 0) {
            dataList.size

        } else {
            0
        }


    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val items = dataList[position]


        holder.bindView(items, itemClickListener)


    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindView(produ: Productos, clickListener: OnItemClickListener) { // crearé una clase para mis usuarios
            // inflaré la imagen de imagenUrl dentro de la vista circleImageView
            Glide.with(context).asBitmap().load(produ.imagen).circleCrop().into(itemView.imagenView)
            Log.e("PRODUCTO",produ.toString())
            itemView.txt_producto.text = produ.producto
            itemView.txt_precio.text = produ.precio

            itemView.setOnClickListener { clickListener.onItemClicked(produ) }
            // implemento el numberPicker
            val valores = Array(101) { it.toString() } // esto es del picker

            val tvValores = itemView.findViewById<TextView>(R.id.tvValores)
            number_picker?.minValue = 0
            number_picker?.maxValue = valores.size - 1
            number_picker?.displayedValues = valores
            number_picker?.setOnValueChangedListener { picker, oldVal, newVal ->
                tvValores?.text = if(newVal == 0) "Agregue al carrito" else "Precio ${(newVal).toDouble() * produ.precio.toDouble()}"
            }

        }

        var producto: TextView? = null
        var precio: TextView? = null
        var imagen: CircleImageView? = null
        var number_picker: NumberPicker? = null

        init {
            producto = itemView.findViewById(R.id.txt_producto)
            precio = itemView.findViewById(R.id.txt_precio)
            imagen = itemView.findViewById(R.id.imagenView)
            number_picker = itemView.findViewById(R.id.number_picker)

        }

    }

}
interface OnItemClickListener{
    fun onItemClicked(producto: Productos)
}
