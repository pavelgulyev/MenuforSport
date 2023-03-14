package com.example.kpapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kp_application.DAO.MyDbManager
import com.example.kp_application.DAO.MyDbNameClass
import com.example.kpapplication.Model.Dish
import com.example.kpapplication.Model.DishAdapter
import com.example.kpapplication.Model.MenuAdapter
import com.example.kpapplication.Model.UserData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_dish_item.*

class MainActivity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    var myAdapter = MenuAdapter(ArrayList(), this)
    var text =""
    var idUd=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    fun initUD(){
        val user = myDbManager.getOneName()
        if(user != null) {
            idUd = user.UserDataID
            Toast.makeText(applicationContext, " UD_id="+idUd, Toast.LENGTH_SHORT).show()
        }
    }
    private fun getSwapMg(): ItemTouchHelper {
        return ItemTouchHelper(object:ItemTouchHelper.
        SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter.removeItem(viewHolder.adapterPosition, myDbManager)
            }
        })
    }
    fun init(){
        rcMenu.layoutManager = LinearLayoutManager(this)
        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(rcMenu)
        rcMenu.adapter = myAdapter

//         Toast.makeText(applicationContext, "к="+user.get(1).UserDataID, Toast.LENGTH_SHORT).show()

    }
    fun getMyIntents() {
        val i = intent
        if (i != null) {
            if (i.getIntExtra("Dish_id", -1)>-1) {
                Toast.makeText(applicationContext, "Dish_id="+i.getIntExtra("Dish_id", -1)+" UD_id="+idUd, Toast.LENGTH_SHORT).show()
                //Log.d("myLog", "Data :$idUd"+i.getIntExtra("Dish_id", -1))
                myDbManager.insertToMenu(i.getIntExtra("Dish_id",-1)!!.toInt(), idUd)
            }
        }
    }
    fun fillResultMenu(list:List<Double>){
            val proteins = String.format("%.1f", list[1])
            val fats = String.format("%.1f", list[2])
            val carbohydrates = String.format("%.1f", list[3])
            val Result = String.format("%.1f", list[0])
            textSquirrels2.text = "Б:" + proteins
            textFats2.text = "Ж:" + fats
            textCarbohydrates2.text = "У:" + carbohydrates
            textRes2.text = "Кал:" + Result
    }
    fun fillAdapter(){
        val list = myDbManager.readMenu()
        myAdapter.updateAdapter(list)
        fillResultMenu(myAdapter.getDish())
        val user = myDbManager.getOneName()
        if(user != null){

            // Toast.makeText(applicationContext, " UD_id="+user.UserDataID, Toast.LENGTH_SHORT).show()
            val proteins= String.format("%.1f", user?.proteins)
            val fats= String.format("%.1f", user?.fats)
            val carbohydrates= String.format("%.1f", user?.carbohydrates)
            val Result= String.format("%.1f", user?.Result)
            textSquirrels.text = "Б:"+proteins
            textFats.text ="Ж:"+ fats
            textCarbohydrates.text = "У:"+carbohydrates
            textRes.text ="Кал:"+Result
        }else{
            textSquirrels.text = "Б:0"
            textFats.text ="Ж:0"
            textCarbohydrates.text = "У:0"
            textRes.text ="Кkал:0"
        }
    }
    override  fun onResume() {
        super.onResume()
        myDbManager.openDb()
        initUD()
        fillAdapter()
        getMyIntents()
    }
    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
    fun onClickAdd(view: View) {
        val i = Intent(this,AddDishActivity::class.java)
        startActivity(i)
    }
    fun onClickBSHU(view: View) {
        val i = Intent(this,EditActivity::class.java)
        startActivity(i)
    }

}