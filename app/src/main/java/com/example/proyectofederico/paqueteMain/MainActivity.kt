package com.example.proyectofederico.paqueteMain

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofederico.*
import com.example.proyectofederico.Actividades.VistaProducto
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnItemClickListener {

    // le daré clic al recycler
    var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: AdaptadorRecycler? = null



    // inicializo el viewModel
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //numberPicker textView para los valores............



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


    }


    private fun observeData(){
        viewModel.fetchUserData().observe(this, Observer {
            swiperefreshlayout.isRefreshing = false
            adapter!!.setListData(ArrayList(it))
            adapter!!.notifyDataSetChanged()
        })
    }

    override fun onItemClicked(producto: Productos) {
        val intent = Intent(this, VistaProducto::class.java)
        intent.putExtra("producto",producto)
        startActivity(intent)
    }


}
