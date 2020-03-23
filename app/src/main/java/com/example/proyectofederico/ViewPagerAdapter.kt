package com.example.proyectofederico

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.proyectofederico.ModelosDeDatos.Productos


class ViewPagerAdapter(private val theViews: Array<Int>, private val theContext: Context, producto: Productos?) : PagerAdapter()
    {
        val producto=producto;
        override fun isViewFromObject(view: View,
                                      `object`: Any): Boolean
        {
            return view === `object`
        }


        override fun getCount(): Int
        {
            return theViews.size
        }

        // Instantiate a new item from the array.
        override fun instantiateItem(container: ViewGroup,
                                     position: Int): Any
        {
            val thisView = theViews[position]
            val inflater = LayoutInflater.from(theContext)

            val layout = inflater.inflate(thisView, container,
                false) as ViewGroup

            container.addView(layout)

          //  val text1:TextView = container.findViewById<TextView>(R.id.textView1)
           // val image1:ImageView= container.findViewById<ImageView>(R.id.image1)



           // text1.setText(producto?.producto)
           // Glide.with(theContext).asBitmap().load(producto?.imagen).into(image1)
            return layout
        }

        override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
            container.removeView(view as View)
        }

    }
