package com.pintu.neostore_kotlin.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pintu.neostore_kotlin.R
import com.pintu.neostore_kotlin.model.APIMsg
import com.pintu.neostore_kotlin.view.register.Register
import com.pintu.neostore_kotlin.viewmodel.LoginVM
import com.pintu.neostore_kotlin.viewmodel.LoginVMFactory

class Login : AppCompatActivity() {

    private lateinit var UserName: EditText
    private lateinit var Password: EditText
    private lateinit var Login: Button
    private lateinit var FAB: FloatingActionButton
    private lateinit var Forgot: TextView

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var UserNames: String
    private lateinit var Passwords: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        UserName = findViewById<EditText>(R.id.ed_username)
        Password = findViewById<EditText>(R.id.ed_password)
        Login = findViewById<Button>(R.id.btn_login)
        FAB = findViewById<FloatingActionButton>(R.id.fab)
        Forgot = findViewById<TextView>(R.id.tv_forgot_pas_link)

        val viewModel = ViewModelProviders.of(this, LoginVMFactory(this)).get(LoginVM::class.java)
        viewModel.getLoginListObserver().observe(this, Observer<APIMsg> {

            if (it != null) {

                Log.d("saurabh", "${it.user_msg}")

            } else {
                Log.d("saurabh", "FAI-Lurre")
            }

        })

        Login.setOnClickListener(View.OnClickListener {
            UserNames = UserName.text.toString()
            Passwords = Password.text.toString()

            if (UserNames.length == 0 || Passwords.length == 0 || !UserNames.matches(emailPattern.toRegex()) || Passwords.length > 0 && Passwords.length < 6) {
                if (UserNames.length == 0) {
                    UserName.requestFocus()
                    UserName.error = "FIELD CANNOT BE EMPTY"
                } else if (UserNames.length != 0 && !UserNames.matches(emailPattern.toRegex())) {
                    UserName.requestFocus()
                    UserName.error = "ENTER VALID USERNAME"
                }
                if (Passwords.length == 0) {
                    Password.requestFocus()
                    Password.error = "FIELD CANNOT BE EMPTY"
                }
                if (Passwords.length > 0 && Passwords.length < 6) {
                    Password.requestFocus()
                    Password.error = "Password Greater than 6 Digit"
                }
            } else {
//                Login.loginVM.makeApiCall(UserNames, Passwords)
//                Login.Login.setVisibility(View.INVISIBLE)
//                Login.progressBar.setVisibility(View.VISIBLE)
                viewModel.makeApiCall(UserNames, Passwords)
                //  Toast.makeText(applicationContext,"Login Success",Toast.LENGTH_SHORT).show()
            }
        })

        FAB.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivityForResult(intent,2)
        })

        Forgot.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,com.pintu.neostore_kotlin.view.forgot.Forgot::class.java)
            startActivityForResult(intent,2)
        })
    }
}

