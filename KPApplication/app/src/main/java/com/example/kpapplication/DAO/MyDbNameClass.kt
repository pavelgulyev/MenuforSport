package com.example.kp_application.DAO

object MyDbNameClass {
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "KP.db"
    /**
     * Table Menu
     */
    const val TABLE_NAME_M= "Menu"
    const val COLUMN_ID_M = "id"
    const val COLUMN_ID_Dish = "DishID"
    const val COLUMN_ID_USERDATA = "UserDataID"
    /**
     * Table Dish
     */
    const val TABLE_NAME_D= "Dish"
    const val COLUMN_NAME = "name"
    const val COLUMN_ID_D = "DishID"
    const val COLUMN_KKAL = "kkal"
    const val COLUMN_CARBOHYDRATES= "carbohydrates"
    const val COLUMN_FATS = "fats"
    const val COLUMN_PROTEINS = "proteins"
    const val COLUMN_WEIGHT_D= "weight"
    /**
     * Table UserData
     */
    const val TABLE_NAME_UD= "UserData"
    const val COLUMN_ID_UD = "UserDataID"
    const val COLUMN_RESULT = "Kkal"
    const val COLUMN_CARBOHYDRATES_UD= "carbohydrates"
    const val COLUMN_FATS_UD = "fats"
    const val COLUMN_PROTEINS_UD = "proteins"
    /**
     * sql Query for create table
     */

    const val CREATE_TABLE_UD = "CREATE TABLE IF  not exists $TABLE_NAME_UD ( $COLUMN_ID_UD INTEGER PRIMARY KEY, " +
            "$COLUMN_CARBOHYDRATES_UD REAL, $COLUMN_FATS_UD REAL, $COLUMN_PROTEINS_UD REAL, $COLUMN_RESULT  REAL);"
    const val CREATE_TABLE_D = "CREATE TABLE IF  not exists $TABLE_NAME_D ( $COLUMN_ID_D INTEGER PRIMARY KEY, " +
            "$COLUMN_KKAL REAL, $COLUMN_CARBOHYDRATES REAL, $COLUMN_FATS REAL, $COLUMN_PROTEINS REAL, $COLUMN_WEIGHT_D REAL, $COLUMN_NAME TEXT);"
    const val CREATE_TABLE_M = "CREATE TABLE IF  not exists $TABLE_NAME_M ( $COLUMN_ID_M INTEGER PRIMARY KEY, " +
            "$COLUMN_ID_Dish INT, $COLUMN_ID_USERDATA INT);"
    const val Insert_TABLE_D = "INSERT INTO $TABLE_NAME_D VALUES " +
            "(1, 129, 26.2, 1.9, 2.1, 100.0, \"Рисовая каша\")," +
            "(2, 186, 31.2, 4.1, 6.3, 100.0, \"Гречневая каша\")," +
            "(3, 128, 0.0, 4.0, 23.1, 100.0, \"Курина грудка Отворная\");"
    const val DELETE_TABLE_UD = "DROP TABLE IF EXISTS $TABLE_NAME_UD"
    const val DELETE_TABLE_D = "DROP TABLE IF EXISTS $TABLE_NAME_D"
    const val DELETE_TABLE_M = "DROP TABLE IF EXISTS $TABLE_NAME_M"
}