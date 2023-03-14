package com.example.kpapplication.Model

class Menu() {
   // var dish = Dish()
    var MenuId:Int = 0
    var UserId:Int = 0
    var DishId:Int = 0
    constructor( MenuId:Int,  UserId:Int,  DishId:Int) : this()
    {
        this.MenuId = MenuId
        this.UserId = UserId
        this.DishId=DishId
    }
    constructor( MenuId:Int,  UserId:Int,  Dish:Int, dish:Dish) : this()
    {
        this.MenuId = MenuId
        this.UserId = UserId
        this.DishId=DishId
       // this.dish=dish
    }
}