package com.example.engn.watermeter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_arsiv.*

class Arsiv : AppCompatActivity() {

    lateinit var dbHandler : MyDBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arsiv)

        title = "Su Arşivi"

        dbHandler = MyDBHandler(this,null,null,1)
        val dbString = dbHandler.databaseToString()

        val listAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,dbString)
        listView.adapter = listAdapter
    }
}
