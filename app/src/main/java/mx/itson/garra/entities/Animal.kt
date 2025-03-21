package mx.itson.garra.entities

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import mx.itson.garra.persistence.GarraDB

class Animal {
    var id = 0
    var apodo = ""
    var especie = ""
    var pasatiempo= ""

    constructor()
    constructor(id:Int, apodo:String, especie:String, pasatiempo:String){
        this.id = id
        this.apodo = apodo
        this.especie = especie
        this.pasatiempo= pasatiempo
    }

    fun save(context: Context, apodo:String, especie: String, pasatiempo:String){
        try {
            val garraDB = GarraDB(context, "GarraDB", null,1)
            val dataBase : SQLiteDatabase = garraDB.writableDatabase
            val values = ContentValues()
            values.put("apodo", apodo)
            values.put("especie", especie)
            values.put("pasatiempo", pasatiempo)

            dataBase.insert("Animales", null, values)
            Toast.makeText(context, "Animal guardado con éxito", Toast.LENGTH_SHORT).show()

            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(500)

            dataBase.close()
        }catch(ex : Exception){
            Log.e("Erro Save animal", "Información: ${ex.message.toString()}")
        }
    }

    fun getAll(context: Context): List<Animal>{
        var animales : MutableList<Animal> = ArrayList()
        try {
            val garraDB = GarraDB(context, "GarraDB", null,1)
            val dataBase : SQLiteDatabase = garraDB.readableDatabase
            val result = dataBase.rawQuery("SELECT id, apodo,especie,pasatiempo FROM Animales", null)
            while(result.moveToNext()){
                val animal=Animal(result.getInt(0),result.getString(1),result.getString(2),result.getString(3))
                animales.add(animal)
            }

        }catch (ex:Exception){
            Log.e("Error getAll animal", "Información ${ex.message.toString()}" )
        }
        Log.d("Animales en DB", "Número de animales: ${animales.size}")
        return animales
    }
}