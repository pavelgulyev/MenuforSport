package com.example.kpapplication.Model

import java.io.Serializable

class UserData()  : Serializable {


    var UserDataID=0
    var Result = 0.0
    var proteins = 0.0
    var fats = 0.0
    var carbohydrates = 0.0

    constructor( UserDataID:Int,Fats: Double, CARBOHYDRATES: Double, Proteins:Double,Result:Double) : this()
    {
        this.UserDataID=UserDataID
        this.proteins=Fats
        this.carbohydrates=CARBOHYDRATES
        this.fats=Proteins
        this.Result=Result
    }



     public fun getResult(height: Double,
                          weight: Double, age: Int, TypeActivityСoefficient: Int,
                          TypeofFormula: Int, sex: Int): Double{
        var Result = 0.0
        var ActivityСoefficient = 0.0
        when(TypeActivityСoefficient)
        {
            1->ActivityСoefficient=1.2
            2->ActivityСoefficient=1.375
            3->ActivityСoefficient=1.5
            4->ActivityСoefficient=1.725
        }
        when(TypeofFormula){

            1->{//МИФФЛИН-САН ЖЕОРА
                when(sex){
                    1->{
                        Result = (10*weight + 6.25 * height - 5 *age +5)*ActivityСoefficient
//                        (10 x вес (кг) + 6.25 x рост (см) – 5 x возраст (г) + 5) x A - для мужчин;
//                        (10 x вес (кг) + 6.25 x рост (см) – 5 x возраст (г) - 161) x A - для женщин.
                    }
                    2->{
                        Result = (10*weight + 6.25 * height - 5 *age -161)*ActivityСoefficient
                    }
                }
            }
            2->when(sex){
                1->{//формула харриса-бенедикта
                    Result = 655+(9.6*weight + 1.8 * height - 4.7 *age)*ActivityСoefficient
                }
                2->{
                    Result = 66+(13.7*weight + 5 * height - 6.76 *age)*ActivityСoefficient
                }
            }
        }


        return Result;
    }

    public fun getPFC(percentFats: Double, percentCARBOHYDRATES: Double, percentProteins:Double){
        if(this.Result!=0.0) {
            this.proteins = this.Result * (percentProteins / 100)
            this.carbohydrates = this.Result * (percentCARBOHYDRATES / 100)
            this.fats = this.Result * (percentFats / 100)
        }
    }
    public fun getPFCDefault(Result:Double){
        this.Result=Result
        if(Result!=0.0){
            this.proteins=this.Result*0.3
            this.carbohydrates=this.Result*0.6
            this.fats=this.Result*0.1
        }
    }
}