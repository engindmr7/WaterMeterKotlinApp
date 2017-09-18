package com.example.engn.watermeter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHandler : MyDBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = MyDBHandler(this ,null,null,1)

        btnsil.setOnClickListener { dbHandler.deleteProduct() }

        arsivbtn.setOnClickListener { view ->
            val gecis = Intent(view.context,Arsiv::class.java)
            startActivityForResult(gecis,0)

        }

        bardakbtn.setOnClickListener { view ->
            dbHandler.addGlass()
            Toast1(view)
        }
           sisebtn.setOnClickListener { view ->
            dbHandler.addBottle()
            Toast2(view)
        }
    }


    fun Toast1(view : View){
        Toast.makeText(this,"200 ml eklendi..",Toast.LENGTH_LONG).show()
    }
    fun Toast2(view : View){
        Toast.makeText(this,"500 ml eklendi..",Toast.LENGTH_LONG).show()
    }
}
