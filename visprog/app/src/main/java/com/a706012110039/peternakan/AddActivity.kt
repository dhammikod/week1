package com.a706012110039.peternakan

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.a706012110039.peternakan.databinding.ActivityAddBinding
import database.globalvar
import model.hewan

class AddActivity : AppCompatActivity() {
    private lateinit var viewbind: ActivityAddBinding
    private lateinit var hewan: hewan
    var position = -1
    var image: String = ""

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }}// GET PATH TO IMAGE FROM GALLEY
            viewbind.imageView2.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
            image = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbind = ActivityAddBinding.inflate(layoutInflater)
        setContentView(viewbind.root)
        supportActionBar?.hide()
        getintent()
        listener()
    }

    private fun getintent(){
        position = intent.getIntExtra("position", -1)
        if(position != -1){
            val hewan = globalvar.listDatahewan[position]
            viewbind.toolbar2.title = "Edit animal"
            viewbind.Addanimal.text = "Edit Animal"
            viewbind.imageView2.setImageURI(Uri.parse(globalvar.listDatahewan[position].imageuri))
            viewbind.usiahewan.editText?.setText(hewan.usiahewan.toString())
            viewbind.namahewan.editText?.setText(hewan.namahewan)
            viewbind.jenishewan.editText?.setText(hewan.jenishewan)
        }
    }

    fun listener(){
        viewbind.imageView2.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewbind.toolbar2.getChildAt(1).setOnClickListener {
            finish()
        }

        viewbind.Addanimal.setOnClickListener {
            var nama = viewbind.namahewan.editText?.text.toString().trim()
            var jenis = viewbind.jenishewan.editText?.text.toString().trim()
            var usia = 0

            hewan = hewan(nama,jenis,usia,image)
            checker()
        }
    }

    private fun checker()
    {
        var isCompleted:Boolean = true

        if(hewan.namahewan!!.isEmpty()){
            viewbind.namahewan.error = "nama hewan cannot be empty"
            isCompleted = false
        }else{
            viewbind.namahewan.error = ""
        }

        if(hewan.jenishewan!!.isEmpty()){
            viewbind.jenishewan.error = "genre cannot be empty"
            isCompleted = false
        }else{
            viewbind.jenishewan.error = ""
        }

        hewan.imageuri = image.toUri().toString()
        if(hewan.imageuri!!.isEmpty()){
            isCompleted = false
        }

        if(viewbind.usiahewan.editText?.text.toString().isEmpty() || viewbind.usiahewan.editText?.text.toString().toInt() < 0)
        {
            viewbind.usiahewan.error = "usia cannot be empty or 0"
            isCompleted = false
        }

        if(isCompleted == true)
        {
            if(position == -1)
            {
                hewan.usiahewan = viewbind.usiahewan.editText?.text.toString().toInt()
                globalvar.listDatahewan.add(hewan)

            }
            else
            {
                hewan.usiahewan = viewbind.usiahewan.editText?.text.toString().toInt()
                globalvar.listDatahewan[position] = hewan
            }
            finish()
        }
    }
}