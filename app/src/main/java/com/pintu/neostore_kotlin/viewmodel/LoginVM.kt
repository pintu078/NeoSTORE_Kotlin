package com.pintu.neostore_kotlin.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pintu.neostore_kotlin.model.APIMsg
import com.pintu.neostore_kotlin.network.RetroInstance
import com.pintu.neostore_kotlin.network.RetroService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class LoginVM(private val context: Context) : ViewModel() {

    lateinit var loginListData: MutableLiveData<APIMsg>

    init {
        loginListData = MutableLiveData<APIMsg>()
    }

    fun getLoginListObserver(): MutableLiveData<APIMsg> {
        return loginListData
    }


    fun makeApiCall(UserName: String, Password: String) {
        Log.d("saurabh", "value $UserName + $Password")
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.createLogin(UserName, Password)
        call.enqueue(object : retrofit2.Callback<APIMsg> {
            override fun onResponse(call: Call<APIMsg>, response: Response<APIMsg>) {
                if (response.isSuccessful) {
                    loginListData.postValue(response.body())
                    Log.d("saurabh", "Success  ${response.body()?.user_msg}")
                    Log.d("saurabh", "Success  ")
                    println("Failure  ${response.body()?.user_msg}")
                    Toast.makeText(context, response.body()?.message, Toast.LENGTH_SHORT).show()
                } else {
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        println("-----DM----------------------------------------")
                        Toast.makeText(
                            context,
                            jObjError.getString("user_msg"),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("saurabh", "Failure  ")
                        Log.d("saurabh", "Failure  ${jObjError.getString("user_msg")}")
                        println("Failure  ${jObjError.getString("user_msg")}")

                    } catch (e: Exception) {
                        Log.d("saurabh", "${e.message}  ")
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<APIMsg>, t: Throwable) {
                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show()
                Log.d("saurabh", "Failure  ")
                Log.d("saurabh", "${t.message}")
            }
        })
    }
}