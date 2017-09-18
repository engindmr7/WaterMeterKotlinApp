package com.example.engn.watermeter

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ENGİN on 16.09.2017.
 */
class MyDBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    var results = ArrayList<String>()

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

        val query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT " + ");"


        sqLiteDatabase.execSQL(query)

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS)
        onCreate(sqLiteDatabase)
    }

    fun addGlass() {
        val values = ContentValues()
        val text = "Bir bardak su içtiniz..(200 ml)" + " " + dateTime.toString()
        values.put(COLUMN_PRODUCTNAME, text)

        val db = writableDatabase
        db.insert(TABLE_PRODUCTS, null, values)
        db.close()

    }

    fun addBottle() {
        val values = ContentValues()
        val text = "Bir şişe su içtiniz..(500 ml) " + " " + dateTime.toString()
        values.put(COLUMN_PRODUCTNAME, text)

        val db = writableDatabase
        db.insert(TABLE_PRODUCTS, null, values)
        db.close()

    }

    val dateTime: String
        get() {
            val zaman = Calendar.getInstance()
            val df = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault())

            val fDate = df.format(zaman.time)
            return fDate
        }


    fun deleteProduct() {
        val db = readableDatabase
        db.delete(TABLE_PRODUCTS, null, null)

    }

    fun databaseToString(): ArrayList<String> {

        var dbString = ""
        val db = writableDatabase
        val query = "SELECT * FROM $TABLE_PRODUCTS WHERE 1 "

        val c = db.rawQuery(query, null)
        c.moveToFirst()

        var name: String? = null

        c.moveToFirst()
        while (!c.isAfterLast) {
            if (c.getString(c.getColumnIndex("productname")) != null) {
                dbString += c.getString(c.getColumnIndex("productname"))
                dbString += "\n"

                name = c.getString(c.getColumnIndex("productname"))

                results.add(" " + name!!)

            }
            c.moveToNext()

        }

        db.close()

        return results
    }

    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "productDB.db"
        private val TABLE_PRODUCTS = "products"
        private val COLUMN_ID = " id"
        private val COLUMN_PRODUCTNAME = "productname"
    }

}

