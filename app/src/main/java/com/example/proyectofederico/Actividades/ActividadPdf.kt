package com.example.proyectofederico.Actividades

import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofederico.Adapters.AdaptadorRecyPdf
import com.example.proyectofederico.ModelosDeDatos.ProductosSeleccionados
import com.example.proyectofederico.R
import com.itextpdf.text.*
import com.itextpdf.text.pdf.*
import kotlinx.android.synthetic.main.activity_pdf.*
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ActividadPdf : AppCompatActivity() {
  // variables del recycler pdf
    var recyclerPdf:RecyclerView? = null
    private var adapter:AdaptadorRecyPdf? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var arrayDatos: ArrayList<ProductosSeleccionados>? = null

    private val STORAGE_CODE: Int = 100

    companion object{
        const val PROD_SELECT = "ProductosSelect"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        arrayDatos = intent?.getSerializableExtra(PROD_SELECT) as ArrayList<ProductosSeleccionados>
        val extraerPrecios: Intent = intent
        var preciosTotales = extraerPrecios.getStringExtra("Total Precios")
        tvTotalPresupuesto.text = preciosTotales
        // inflarè el recyclerView
        recyclerPdf = findViewById(R.id.RecyclerPdf)
        recyclerPdf?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerPdf?.layoutManager = layoutManager
        // adaptador del recyclerPdf
        adapter = AdaptadorRecyPdf(arrayDatos!!)
        recyclerPdf?.adapter = adapter




        saveButton?.setOnClickListener {
            //necesitamos manejar permisos de tiempo de ejecución para dispositivos con marshmallow  y superior
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                //sistema operativo> = marshMallow (6.0), verifique que el permiso esté habilitado o no
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    // no se otorgó permiso, solicítelo
                    val permissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permissions, STORAGE_CODE)
                }
                else{
                    //permiso ya otorgado, llamar al método savepdf
                    savePdf()
                }
            }
            else{
                //permiso ya otorgado, llamar al método savepdf
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    savePdf()
                }
            }
        }


    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun savePdf(){
        // crear objeto de la clase document
        val mDoc = com.itextpdf.text.Document()
        // pdf. nombre del archivo
        val mFileName = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())
        val mFilePath = Environment.getExternalStorageDirectory().toString() + "/" + mFileName + ".Pdf"
        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))
            mDoc.open()

            val mPrecioTotal = tvTotalPresupuesto.text.toString()
            val mPro = tvPro.text.toString()
            val mCan = tvCan.text.toString()
            val mPRe = tvPre.text.toString()
            val mTot = tvTot.text.toString()

            mDoc.addAuthor("Hernan Torres")

            val table = PdfPTable(4)

            table.headerRows = 1
            table.addCell(PdfPCell(Phrase("Producto", Font(Font.FontFamily.HELVETICA, 16f,Font.BOLD))))
            table.addCell(PdfPCell(Phrase("Cantidad", Font(Font.FontFamily.HELVETICA, 16f,Font.BOLD))))
            table.addCell(PdfPCell(Phrase("Precio", Font(Font.FontFamily.HELVETICA, 16f,Font.BOLD))))
            table.addCell(PdfPCell(Phrase("Subtotal", Font(Font.FontFamily.HELVETICA, 16f,Font.BOLD))))

            for (list in arrayDatos!!){
                table.addCell(PdfPCell(Phrase(list.producto, Font(Font.FontFamily.HELVETICA, 12f))))
                table.addCell(PdfPCell(Phrase(list.cantidad.toString(), Font(Font.FontFamily.HELVETICA, 12f))))
                table.addCell(PdfPCell(Phrase(list.precio.toString(), Font(Font.FontFamily.HELVETICA, 12f))))
                table.addCell(PdfPCell(Phrase(list.precioTotal.toString(), Font(Font.FontFamily.HELVETICA, 12f))))
            }
         //   val arrayLista = arrayListOf("$mPro                                  $mCan                                   $mPro                        $mTot")
         //   mDoc.add(Paragraph(arrayLista.toString()))
           /* for (list in arrayLista){
                val lista = "$mPro \t $mCan \t $mPRe \t $mTot"
                mDoc.add(Paragraph(lista))
            }*/



          //  for (dat in  arrayDatos!!){
            //    val text = "${dat.producto}                                          \t ${dat.cantidad}                             \t ${dat.precio}                        \t ${dat.precioTotal}"
              //  mDoc.add(Paragraph(text))
            //}

            mDoc.add(table)
            val preT =Paragraph(mPrecioTotal, Font(Font.FontFamily.HELVETICA, 15f, Font.BOLD))
            preT.alignment = Element.ALIGN_RIGHT
            mDoc.add(preT)

            mDoc.close()
            Toast.makeText(this, " $mFileName.pdf\nse guardó en \n$mFilePath", Toast.LENGTH_SHORT).show()

        }
        catch (e: Exception){}

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            STORAGE_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // se otorgó el permiso de la ventana emergente, llama a savePdf()
                    savePdf()
                }
                else {
                    // se denegó el permiso de la ventana emergente, muestra mensaje de error
                    Toast.makeText(this, "permiso denegado", Toast.LENGTH_SHORT).show()


                }
            }
        }
        // esto en el activity principal
    }

      //  tvNombreProducto.text = text

        // esto en una clase aparte jutno con lo demas que le explique..
        // luego en el boton en la lista principal, se coloca generar boton. y listo.
        // no es necesario un EditText
        //osea. en vez de usar una actividad utilizo una clase. como class Pdf??
        // si, y solo cambias y omites las cosas que usan la activity
        // le falta aprender varias cosas






}

