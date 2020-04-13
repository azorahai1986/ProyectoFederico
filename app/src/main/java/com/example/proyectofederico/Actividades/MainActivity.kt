package com.example.proyectofederico.Actividades

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.proyectofederico.*
import com.example.proyectofederico.Adapters.AdaptadorRecycler
import com.example.proyectofederico.Adapters.CartelAdapter
import com.example.proyectofederico.Adapters.OnItemClickListener
import com.example.proyectofederico.ModelosDeDatos.MainViewModel
import com.example.proyectofederico.ModelosDeDatos.Productos
import com.example.proyectofederico.ModelosDeDatos.ProductosSeleccionados
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnItemClickListener {

    // le daré clic al recycler
    var recyclerView: RecyclerView? = null
    var cartelPrincipal: ViewPager2? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: AdaptadorRecycler? = null
    private var adapter1: CartelAdapter? = null
    var btVistaPrevia: Button? = null
    var datosDelRecycler:String? = null

    private var datosProductos: ArrayList<Productos>? = null
    private var datosPrecios: ArrayList<Double>? = null




    // inicializo el viewModel
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
// para darle movimiento al cartel de la pantalla inicial
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

        // enviaré los productos seleccionados a la actividad pdf............................
        btVistaPrevia = findViewById(R.id.btVistaPrevia)

        btVistaPrevia?.setOnClickListener {
            datosProductos = adapter?.dataList
            val datosProdSelec = arrayListOf<ProductosSeleccionados>()
            if(datosProductos!=null) {
                for (index in 0 until datosProductos!!.size) {
                    if(datosPrecios!![index] > 0){
                        val prodS = ProductosSeleccionados()
                        prodS.producto = datosProductos!![index].producto
                        prodS.precio = datosProductos!![index].precio.toDouble()
                        prodS.cantidad = (datosPrecios!![index] / prodS.precio).toInt()
                        prodS.precioTotal = datosPrecios!![index]
                        datosProdSelec.add(prodS)
                    }
                }
                val intent = Intent(this, ActividadPdf::class.java)
                intent.putExtra(ActividadPdf.PROD_SELECT, datosProdSelec)
                startActivity(intent)
            }
            // dar el intent de los productos seleccionados hacia actividadPdf
        }
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

        adapter = AdaptadorRecycler(productos, applicationContext, this, this
        )

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
    // metodo para traer los precio del recycer y suumaros en el textview. debajo del cartel principal
    fun sumarTotalPrecio(precios: ArrayList<Double>){
        var sum = 0.0
        for(x in precios)
            sum += x
        val text = "Total Precios: $sum"
        tvTotalPrecios.text = text

        datosPrecios = precios
    }
    fun obtenerDatos(datos: ArrayList<String>){
        var dat = ""
        for (i in datos)
            dat += i
        val text = "los datos en Array son $dat"

        datosDelRecycler = text
       // Log.e("funcion Obtener datos ", "contiene $dat")

    }

}
