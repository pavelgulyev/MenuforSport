package com.example.kpapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.kp_application.DAO.MyDbManager
import com.example.kpapplication.Model.UserData
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_edit.view.*
import java.text.SimpleDateFormat
import java.util.*

class EditActivity : AppCompatActivity() {
    var sexUser = -1
    var ActivityCoef =-1
    var TypeF = -1
    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val sexArray = resources.getStringArray(R.array.Sex)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, sexArray
        )

        sex.adapter = adapter
        sex.
        sex.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                sexUser = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        val ActivityСoefficientArray = resources.getStringArray(R.array.Activity)
        val adapter2 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, ActivityСoefficientArray
        )
        ActivityСoefficient.adapter = adapter2
        ActivityСoefficient.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                ActivityCoef = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        val TypeFormula = resources.getStringArray(R.array.TypeFormula)
        val adapter3 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, TypeFormula
        )
        TypeofFormula.adapter = adapter3
        TypeofFormula.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                TypeF = position
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
    }
     fun onClickSave(view: View) {
        val userheight = editheight.text.toString().toDouble()
        val userweight = editweight.text.toString().toDouble()
        val userage = editAge.text.toString().toInt()
        var userData = UserData()
         userData.getPFCDefault(userData.getResult(userheight, userweight, userage, ActivityCoef, TypeF, sexUser))
         //myDbManager.updateUserData(1,userData.fats,userData.carbohydrates,userData.proteins, userData.Result)
         // Kotlin
         myDbManager.insertUserData(userData)
         val text = "kkal "+userData.Result
         val duration = Toast.LENGTH_SHORT

         val toast = Toast.makeText(applicationContext, text, duration)
         toast.show()
//        if((userheight !=0.0) and (userweight !=0.0) and (userage !=0) and(sexUser !=0)and
//            (ActivityCoef !=0)and(TypeF !=0))
//        {
//
//        }

        val i = Intent(this,MainActivity::class.java)
//         i.putExtra('id', )
        startActivity(i)

    }
    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

}