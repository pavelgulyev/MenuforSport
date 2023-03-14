package com.example.kpapplication.Model

import java.io.Serializable

class Dish()   : Serializable {
    var DishID:Int = 0
    var kkal: Double = 0.0
    var proteins: Double = 0.0
    var fats: Double = 0.0
    var carbohydrates: Double = 0.0
    var weight: Double = 0.0
    var name:String = ""

    constructor( DishID:Int,  kkal: Double,  proteins: Double,  fats: Double, carbohydrates: Double, weight: Double, name:String) : this()
    {
       this.kkal=kkal
       this.proteins=proteins
       this.fats=fats
       this.carbohydrates=carbohydrates
       this.weight=weight
       this.DishID= DishID
       this.name=name
    }
}