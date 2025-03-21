package mx.itson.garra.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import mx.itson.garra.R
import mx.itson.garra.entities.Animal

class AnimalAdapter(private val context: Context, private val animales: List<Animal>) : BaseAdapter() {

    override fun getCount(): Int {
        return animales.size
    }

    override fun getItem(position: Int): Animal {
        return animales[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.elem_animal, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        try {
            val animal = getItem(position)
            viewHolder.txtApodo.text = animal.apodo
            viewHolder.txtEspecie.text = animal.especie
            viewHolder.txtPasatiempo.text = animal.pasatiempo
        } catch (ex: Exception) {
            Log.e("Error al renderizar animales", ex.message.toString())
        }

        return view
    }

    private class ViewHolder(view: View) {
        val txtApodo: TextView = view.findViewById(R.id.txt_apodo)
        val txtEspecie: TextView = view.findViewById(R.id.txt_especie)
        val txtPasatiempo: TextView = view.findViewById(R.id.txt_pasatiempo)
    }
}