package com.example.kp_application.DAO

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler (context: Context       ): SQLiteOpenHelper(context, MyDbNameClass.DATABASE_NAME, null, MyDbNameClass.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(MyDbNameClass.CREATE_TABLE_D)
        db.execSQL(MyDbNameClass.CREATE_TABLE_UD)
        db.execSQL(MyDbNameClass.CREATE_TABLE_M)
        db.execSQL(MyDbNameClass.Insert_TABLE_D)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(MyDbNameClass.DELETE_TABLE_UD)
        db.execSQL(MyDbNameClass.DELETE_TABLE_D)
        db.execSQL(MyDbNameClass.DELETE_TABLE_M)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }


}
