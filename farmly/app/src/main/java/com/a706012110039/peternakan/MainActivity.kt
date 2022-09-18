package com.a706012110039.peternakan

import `interface`.cardlistener
import adapter.rvadapter
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.a706012110039.peternakan.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import database.globalvar

class MainActivity : AppCompatActivity(),cardlistener {
    private lateinit var viewbind: ActivityMainBinding
    private val adapter = rvadapter(globalvar.listDatahewan, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        viewbind = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewbind.root)
        listener()
        setuprv()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()

        if(globalvar.listDatahewan.size>0){
            viewbind.textView2.alpha = 0f
        }else{
            viewbind.textView2.alpha = 1f
        }
    }

    fun listener(){
        viewbind.addbutton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    fun setuprv(){
        val layoutManager = LinearLayoutManager(baseContext)
        viewbind.listdata.layoutManager = layoutManager
        viewbind.listdata.adapter = adapter
    }

    override fun onCardClick(isEdit : Boolean,position: Int) {
        if(!isEdit){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete animal")
            builder.setMessage("Are you sure you want to delete this animal?")
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { function, which ->
                val snackbar = Snackbar.make(viewbind.listdata, "Animal Deleted", Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction("Dismiss") { snackbar.dismiss() }
                snackbar.setActionTextColor(Color.WHITE)
                snackbar.setBackgroundTint(Color.GRAY)
                snackbar.show()
//tes github berfungsi
                //remove
                globalvar.listDatahewan.removeAt(position)
                adapter.notifyDataSetChanged()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }else{
            val intent = Intent(this, AddActivity::class.java).putExtra("position",position)
            startActivity(intent)
        }


    }
}