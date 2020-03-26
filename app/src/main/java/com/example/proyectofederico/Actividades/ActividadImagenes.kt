package com.example.proyectofederico.Actividades

import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofederico.*
import com.example.proyectofederico.Adapters.ImagenesAdapter
import com.example.proyectofederico.ModelosDeDatos.Productos
import com.example.proyectofederico.R
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.activity_actividad_imagenes.*
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*

class ActividadImagenes : AppCompatActivity() {

    private val STORAGE_CODE: Int = 100

    //lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_imagenes)
        saveButton.setOnClickListener {
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

            val mText = textEt.text.toString()
            mDoc.addAuthor("Hernan Torres")
            mDoc.add(Paragraph(mText))
            mDoc.close()
            Toast.makeText(this, " $mFileName.pdf\nse gusrdó en \n$mFilePath", Toast.LENGTH_SHORT).show()
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
    }


        /* val producto = intent.getSerializableExtra("producto") as? Productos
         Log.e("PRODUCTO",producto.toString())


         var imagenes = producto!!.imagenes
         Log.e("CANT",imagenes.size.toString())
         //adapter = ViewPagerAdapter(myViews, this,producto)*/


}

