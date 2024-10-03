package com.example.textsaver

import android.app.Activity
import android.content.SharedPreferences
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

private const val STRORAGE_NAME = "localShared"
private const val KEY_NAME = "localKey"
private const val FILE_NAME = "some_text.txt"

class Repository(val activity:Activity) {

    lateinit var sharedPref:SharedPreferences

    private fun getDataFromSharedPreference(): String? {
        sharedPref = activity.getSharedPreferences(STRORAGE_NAME, 0)
        return sharedPref.getString(KEY_NAME, null)
    }

    private fun getDataFromLocalVariable(): String?{
        var fis: FileInputStream? = null

        try {
            fis = activity.openFileInput(FILE_NAME)
            val bytes =  ByteArray(fis.available())
            fis.read(bytes)
            return String(bytes)
        }catch (ex: IOException){

        }finally {
            fis?.close()
        }

        return null
    }

    fun saveText(text: String){
        sharedPref = activity.getSharedPreferences(STRORAGE_NAME, 0)
        val editor = sharedPref.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()

        var fos: FileOutputStream? = null

        try {
            fos = activity.openFileOutput(FILE_NAME, 0)
            fos.write(text.toByteArray())
        }catch (ex: IOException){

        }finally {
            fos?.close()
        }
    }

    fun clearText(){
        sharedPref = activity.getSharedPreferences(STRORAGE_NAME, 0)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()

        activity.deleteFile(FILE_NAME)
    }

    fun getText(): String{
        var text = getDataFromLocalVariable()

        if (text == null){
            text = getDataFromSharedPreference()
        }

        if (text == null) return ""

        return text
    }

}