package com.pintu.neostore_kotlin.view.forgot

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pintu.neostore_kotlin.R
import com.pintu.neostore_kotlin.model.APIMsg
import com.pintu.neostore_kotlin.view.login.Login
import com.pintu.neostore_kotlin.viewmodel.ForgotVM

class Forgot : AppCompatActivity() {
    private lateinit var Email: EditText
    private lateinit var Submit: Button

    private lateinit var Emails: String
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        Email = findViewById(R.id.ed_email)
        Submit = findViewById(R.id.btn_submit)

        val viewModel = ViewModelProviders.of(this).get(ForgotVM::class.java)
        viewModel.getForgotListObserver().observe(this, Observer<APIMsg> {

            if (it != null) {

                Log.d("saurabh", "${it.user_msg}")
                val intent = Intent(this, Login::class.java)
                setResult(2,intent)
                finish()
                //startActivity(intent)

            } else {
                Log.d("saurabh", "FAI-Lurre")
            }

        })

        Submit.setOnClickListener(View.OnClickListener {
            Emails = Email.text.toString()

            if (Emails.length == 0 || !Emails.matches(emailPattern.toRegex())) {
                if (Emails.length == 0) {
                    Email.requestFocus()
                    Email.error = "FIELD CANNOT BE EMPTY"
                } else if (Emails.length != 0 && !Emails.matches(emailPattern.toRegex())) {
                    Email.requestFocus()
                    Email.error = "Invalid Email"
                }
            } else {
                viewModel.makeForgotApiCall(Emails)
            }
        })

    }
}