package com.example.kp_application.DAO

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.kpapplication.Model.Dish
import com.example.kpapplication.Model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyDbManager(context: Context) {
    val myDBHelper = DatabaseHandler(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = myDBHelper.writableDatabase
    }

    /**
     * insert in Menu
     */
     fun insertToMenu(Dish: Int, UserData: Int){
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_ID_D, Dish)
            put(MyDbNameClass.COLUMN_ID_UD, UserData)
        }
        db?.insert(MyDbNameClass.TABLE_NAME_M,null, values)
    }
     fun insertUserData(userData:UserData) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_RESULT, userData.Result)
            put(MyDbNameClass.COLUMN_PROTEINS, userData.proteins)
            put(MyDbNameClass.COLUMN_CARBOHYDRATES, userData.carbohydrates)
            put(MyDbNameClass.COLUMN_FATS, userData.fats)
        }
        db?.insert(MyDbNameClass.TABLE_NAME_UD,null, values)
    }
//    /**
//     * update in UserData
//     */
//    fun updateUserData(UserDataID:Int,Fats: Double, CARBOHYDRATES: Double, Proteins:Double, Result:Double) {
//        val selection = MyDbNameClass.COLUMN_ID_UD + "=$UserDataID"
//        val values = ContentValues().apply {
//            put(MyDbNameClass.COLUMN_FATS_UD, Fats)
//            put(MyDbNameClass.COLUMN_CARBOHYDRATES_UD, CARBOHYDRATES)
//            put(MyDbNameClass.COLUMN_PROTEINS_UD, Proteins)
//            put(MyDbNameClass.COLUMN_RESULT, Result)
//        }
//        db?.update(MyDbNameClass.TABLE_NAME_UD, values, selection, null)
//    }
    /**
     * update in Menu
     */
//    suspend fun updateMenu(Dish: Int, UserData: Int, MenuId: Int) = withContext(Dispatchers.IO){
//        val selection = BaseColumns._ID + "=$MenuId"
//        val values = ContentValues().apply {
//            put(MyDbNameClass.COLUMN_ID_UD, Dish)
//            put(MyDbNameClass.COLUMN_ID_D, UserData)
//        }
//        db?.update(MyDbNameClass.TABLE_NAME_M, values, selection, null)
//    }
    /**
     * remove in Menu
     */
    fun removeMenu( id: String){
        val selection = MyDbNameClass.COLUMN_ID_Dish + "=$id"
        db?.delete(MyDbNameClass.TABLE_NAME_M,selection, null)
    }
//    fun removeDish( id: String){
//        val selection = MyDbNameClass.COLUMN_ID_D + "=$id"
//        db?.delete(MyDbNameClass.TABLE_NAME_D,selection, null)
//    }


@SuppressLint("Range")
fun readMenu(): ArrayList<Dish> {
        val dataList = ArrayList<Dish>()
        val sql = "Select Menu.DishID, Dish.kkal, Dish.carbohydrates, Dish.fats, Dish.weight, Dish.name,Dish.proteins   from Menu " +
                "join Dish on Menu.DishID = Dish.DishID " +
                "join UserData on Menu.UserDataID = UserData.UserDataID"
        val cursor = db?.rawQuery(sql,null)

        while (cursor?.moveToNext()!!) {
            val name = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME))
            val kkal =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_KKAL))
            val carbohydrates =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_CARBOHYDRATES))
            val DishID =
                cursor.getInt(cursor.getColumnIndex(MyDbNameClass.COLUMN_ID_D))
            val protein =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_PROTEINS))
            val fats =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_FATS))
            val weight =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_WEIGHT_D))
            val item = Dish(DishID,kkal, protein, fats, carbohydrates, weight, name)
            dataList.add(item)
        }
        cursor.close()
        return dataList
    }
    @SuppressLint("Range", "Recycle")
    fun getOneName(): UserData? {
        val dataList = ArrayList<UserData>()
        val cursor = db?.query(
            MyDbNameClass.TABLE_NAME_UD, null,
            null, null,
            null, null, null
        )
        while (cursor?.moveToNext()!!) {
            val kkal =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_RESULT))
            val carbohydrates =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_CARBOHYDRATES))
            val UserDataID =
                cursor.getInt(cursor.getColumnIndex(MyDbNameClass.COLUMN_ID_UD))
            val protein =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_PROTEINS))
            val fats =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_FATS))
            dataList.add(UserData(UserDataID, protein, fats, carbohydrates, kkal))
        }
        if (dataList.size-1 >= 0){
            return dataList[dataList.size-1]
        }else{
            return null
        }
    }
    @SuppressLint("Range")
    fun readDish(searchText:String): ArrayList<Dish>  {
        val dataList = ArrayList<Dish>()
        val selection = "${MyDbNameClass.COLUMN_NAME} like ?"
        val cursor = db?.query(
            MyDbNameClass.TABLE_NAME_D, null, selection, arrayOf("%$searchText%"),
            null, null, null
        )
        while (cursor?.moveToNext()!!) {
            val name = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME))
            val kkal =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_KKAL))
            val carbohydrates =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_CARBOHYDRATES))
            val DishID =
                cursor.getInt(cursor.getColumnIndex(MyDbNameClass.COLUMN_ID_D))
            val protein =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_PROTEINS))
            val fats =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_FATS))
            val weight =
                cursor.getDouble(cursor.getColumnIndex(MyDbNameClass.COLUMN_WEIGHT_D))
            val item = Dish(DishID,kkal, protein, fats, carbohydrates, weight, name)
            dataList.add(item)
        }
        cursor.close()
        return dataList
    }
    fun closeDb(){
        myDBHelper.close()
    }

}