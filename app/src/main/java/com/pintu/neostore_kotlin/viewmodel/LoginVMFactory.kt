package com.pintu.neostore_kotlin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pintu.neostore_kotlin.view.login.Login
import java.lang.IllegalArgumentException

class LoginVMFactory(private val login: Login) : ViewModelProvider.Factory {
    private val context: Context

    init {
        context = login
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginVM::class.java)) {
            return LoginVM(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}