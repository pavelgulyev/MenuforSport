package com.example.kpapplication.Model

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kpapplication.R
import android.content.Context
import com.example.kp_application.DAO.MyDbNameClass
import com.example.kpapplication.MainActivity

class DishAdapter (listMain: ArrayList<Dish>, contextM: Context) : RecyclerView.Adapter<DishAdapter.MyHolder>() {
    var listArray = listMain;
    var context = contextM
    class MyHolder(itemView: View, contextV:Context) : RecyclerView.ViewHolder(itemView){
        val KkalText = itemView.findViewById<TextView>(R.id.textKkal)
        var TextName = itemView.findViewById<TextView>(R.id.textName)
        var textP = itemView.findViewById<TextView>(R.id.textP)
        var textF = itemView.findViewById<TextView>(R.id.textF)
        var textU = itemView.findViewById<TextView>(R.id.textU)
        var textW = itemView.findViewById<TextView>(R.id.textWeight)
        val context = contextV
        fun setData(result: Dish) {
            KkalText.text =result.kkal.toString()+" калорий"
            TextName.text = result.name
            textP.text = result.proteins.toString()
            textF.text = result.fats.toString()
            textU.text = result.carbohydrates.toString()
            textW.text = result.weight.toString()
            itemView.setOnClickListener {
//                val intent = Intent(context,MainActivity::class.java)
                val intent = Intent(context,MainActivity::class.java).apply {
                    putExtra("Dish_id", result.DishID)
                }
                context.startActivity(intent)
            }
        }

    }

    /**
     * Создаем наш шаблон
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyHolder(inflater.inflate(R.layout.menu_dish_item, parent, false), context)
    }

    /**
     * создано
     */
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArray.get(position))
    }

    /**
     * Сколько элементов во ViewHolder
     *
     */
    override fun getItemCount(): Int {
        return listArray.size;
    }
    fun updateAdapter(listItems:List<Dish>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }
    fun getDish(): List<Double>{
        var map= ArrayList<Double>()
        var sumK = 0.0
        var sumP = 0.0
        var sumF = 0.0
        var sumC = 0.0
        for (n in listArray){
            sumK+=n.kkal
            sumP+=n.proteins
            sumF+=n.fats
            sumC+=n.carbohydrates
        }
        map.add(sumK)//0
        map.add(sumP)//1
        map.add(sumF)//2
        map.add(sumC)//3
        return map
    }
}