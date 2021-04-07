package com.pintu.neostore_kotlin.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pintu.neostore_kotlin.model.APIMsg
import com.pintu.neostore_kotlin.network.RetroInstance
import com.pintu.neostore_kotlin.network.RetroService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class RegisterVM(application: Application) : AndroidViewModel(application) {

    lateinit var registerList : MutableLiveData<APIMsg>

    init {
        registerList = MutableLiveData()
    }

    fun getRegisterListObserver():MutableLiveData<APIMsg>{
        return registerList
    }

    fun makeRegisterApiCall(fName:String,lName:String,email:String,pass:String,cPass:String,gender:String,phone:String){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.registerPost(fName,lName,email,pass,cPass,gender,phone)
        call.enqueue(object : retrofit2.Callback<APIMsg> {
            override fun onResponse(call: Call<APIMsg>, response: Response<APIMsg>) {
                if (response.isSuccessful) {
                    registerList.postValue(response.body())
                    Log.d("saurabh", "Success  ${response.body()?.user_msg}")
                    Toast.makeText(getApplication(), response.body()?.message, Toast.LENGTH_SHORT).show()
                } else {
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        println("-----DM----------------------------------------")
                        Toast.makeText(
                            getApplication(),
                            jObjError.getString("user_msg"),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("saurabh", "Failure  ${jObjError.getString("user_msg")}")
                    } catch (e: Exception) {
                        Log.d("saurabh", "${e.message}  ")
                        Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<APIMsg>, t: Throwable) {
                Toast.makeText(getApplication(), "Check Internet Connection", Toast.LENGTH_SHORT).show()
                Log.d("saurabh", "${t.message}")
            }
        })
    }
}


