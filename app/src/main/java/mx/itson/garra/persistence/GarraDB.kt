package mx.itson.garra.persistence

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class GarraDB(context: Context?, name:String?, factory:SQLiteDatabase.CursorFactory?, version:Int):
    SQLiteOpenHelper(context,name,factory,version) {
    override fun onCreate(sqliteDatabase: SQLiteDatabase) {
        try {
            sqliteDatabase.execSQL("CREATE TABLE Animales (id INTEGER PRIMARY KEY AUTOINCREMENT, apodo TEXT, especie TEXT, pasatiempo TEXT )")
        }catch (ex: Exception){
            Log.e("Error al crear la base de datos", "Info: ${ex.message.toString()}")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}