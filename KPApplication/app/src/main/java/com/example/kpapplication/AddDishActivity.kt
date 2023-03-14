package com.example.kpapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kp_application.DAO.MyDbManager
import com.example.kpapplication.Model.DishAdapter
import kotlinx.android.synthetic.main.activity_add_dish.*


class AddDishActivity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    var myAdapter = DishAdapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dish)
        init()
        getMyIntents()
    }
    fun init(){
        listDish.layoutManager = LinearLayoutManager(this)
        listDish.adapter = myAdapter
    }
    fun fillAdapter(text: String){
        val list = myDbManager.readDish(text)
        //Toast.makeText(applicationContext, "к="+list.size, Toast.LENGTH_LONG).show()
        myAdapter.updateAdapter(list)

    }
    fun getMyIntents() {
        val i = intent
        if (i != null) {
            if (i.getIntExtra("id", -1)>-1) {
                Toast.makeText(applicationContext, "id="+i.getIntExtra("id", -1), Toast.LENGTH_SHORT).show()

            }
        }
    }
    fun onSearch(view: View) {
        fillAdapter(editDish.text.toString())
    }
    override  fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter("")

    }

}