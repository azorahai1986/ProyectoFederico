package com.example.proyectofederico.paqueteMain

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.proyectofederico.*
import com.example.proyectofederico.Actividades.VistaProducto
import com.example.proyectofederico.Adapters.CartelAdapter
import com.example.proyectofederico.Adapters.ImagenesAdapter
import com.example.proyectofederico.ModelosDeDatos.Carteles
import com.example.proyectofederico.ModelosDeDatos.Productos
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnItemClickListener {

    // le daré clic al recycler
    var recyclerView: RecyclerView? = null
    var cartelPrincipal: ViewPager2? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: AdaptadorRecycler? = null
    private var adapter1: CartelAdapter? = null



    // inicializo el viewModel
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    private val handler = Handler()
    private val runnable = Runnable {
        if(adapter1?.images?.size != 0) {
            cartelPrincipal?.currentItem = if (cartelPrincipal!!.currentItem == adapter1!!.images.size-1) 0
            else cartelPrincipal!!.currentItem+1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // trataré de inflar el cartelAdapter

         cartelPrincipal = findViewById(R.id.ViewPagerCartel)
        adapter1 = CartelAdapter(mutableListOf(), this)
        cartelPrincipal?.adapter = adapter1

        cartelPrincipal?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 3000)
            }
        })



        // para actualizar la app moviendo el dedo hacia abajo

        swiperefreshlayout.setOnRefreshListener {
            observeData()
        }
        // sera mi adaptador

        //asocio el recycler a mi main activity
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager
        val productos = ArrayList<Productos>()

        adapter = AdaptadorRecycler(productos, applicationContext, this)
         // esto es muy parecido a un listview. asi que deberia crear un ArrayList. arriba de lista. debajo del onCreate
        recyclerView?.adapter = adapter


        observeData()
        observeDataCartel()

    }


    private fun observeData(){
        viewModel.fetchUserData().observe(this, Observer {
            swiperefreshlayout.isRefreshing = false
            adapter!!.setListData(ArrayList(it))
            adapter!!.notifyDataSetChanged()
        })
    }
    private fun observeDataCartel(){
        viewModel.fetchUserDataCartel().observe(this, Observer {
            adapter1?.images = it.toArray()
            adapter1?.notifyDataSetChanged()
        })
    }

    override fun onItemClicked(producto: Productos) {
        val intent = Intent(this, VistaProducto::class.java)
        intent.putExtra("producto",producto)
        startActivity(intent)
    }


}
