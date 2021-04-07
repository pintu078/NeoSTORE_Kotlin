package com.pintu.neostore_kotlin.view.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pintu.neostore_kotlin.R
import com.pintu.neostore_kotlin.model.APIMsg
import com.pintu.neostore_kotlin.view.login.Login
import com.pintu.neostore_kotlin.viewmodel.RegisterVM

class Register : AppCompatActivity() {

    private lateinit var FirstName: EditText
    private lateinit var LastName: EditText
    private lateinit var Email: EditText
    private lateinit var Password: EditText
    private lateinit var CPassword: EditText
    private lateinit var Male: RadioButton
    private lateinit var Female: RadioButton
    private lateinit var Phone: EditText
    private lateinit var rbcheckbox: RadioButton
    private lateinit var Register: Button
    private lateinit var tvGenderEr: TextView
    private lateinit var tvChkboxEr: TextView
    private lateinit var rdgGender: RadioGroup
    private lateinit var rdgChkbox: RadioGroup

    private lateinit var Fnames: String
    private lateinit var Lnames: String
    private lateinit var Emails: String
    private lateinit var Passwords: String
    private lateinit var CPasswords: String
    private lateinit var genders: String
    private lateinit var Phones: String

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var namePattern = "[a-zA-Z]+"
    var passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        FirstName = findViewById(R.id.ed_first_name)
        LastName = findViewById(R.id.ed_last_name)
        Email = findViewById(R.id.ed_email)
        Password = findViewById(R.id.ed_password)
        CPassword = findViewById(R.id.ed_c_password)
        Male = findViewById(R.id.male)
        Female = findViewById(R.id.female)
        Phone = findViewById(R.id.ed_phone)
        rbcheckbox = findViewById(R.id.chkbox)
        Register = findViewById(R.id.btn_register)
        tvGenderEr = findViewById(R.id.tv_gender_er)
        tvChkboxEr = findViewById(R.id.tv_chkbox)
        rdgGender = findViewById(R.id.rdg_gender)
        rdgChkbox = findViewById(R.id.rdg_chkbox)

        val viewModel = ViewModelProviders.of(this).get(RegisterVM::class.java)
        viewModel.getRegisterListObserver().observe(this, Observer<APIMsg> {

            if (it != null) {

                Log.d("saurabh", "${it.user_msg}")
                val intent = Intent(this,Login::class.java)
                setResult(2,intent)
                finish()
                //startActivity(intent)

            } else {
                Log.d("saurabh", "FAI-Lurre")
            }

        })

        Register.setOnClickListener(View.OnClickListener {

            Fnames = FirstName.text.toString().trim()
            Lnames = LastName.text.toString().trim()
            Emails = Email.text.toString().trim()
            Passwords = Password.text.toString().trim()
            CPasswords = CPassword.text.toString().trim()
            Phones = Phone.text.toString()

            val isSelected_Gender: Int = rdgGender.checkedRadioButtonId
            val isSelected_Chkbox: Int = rdgChkbox.checkedRadioButtonId

            class Radio_Check : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    if (Male.isChecked) {
                        tvGenderEr.visibility = View.GONE
                        tvGenderEr.error = null
                        genders = Male.text.toString().substring(0, 1)
                        println("---------------------------------------------$genders")

                    } else if (Female.isChecked) {
                        tvGenderEr.visibility = View.GONE
                        tvGenderEr.error = null
                        genders = Female.text.toString().substring(0, 1)
                        println("---------------------------------------------$genders")
                    }
                    if (rbcheckbox.isChecked()) {
                        tvChkboxEr.visibility = View.GONE
                        tvChkboxEr.error = null
                    }
                }
            }

            if (Fnames.length == 0 || Lnames.length == 0 || Emails.length == 0 || Passwords.length == 0 || CPasswords.length == 0 || Phones.length == 0 || isSelected_Chkbox == -1 || isSelected_Gender == -1 || !Fnames.matches(
                    namePattern.toRegex()
                ) || !Lnames.matches(namePattern.toRegex()) || !Emails.matches(emailPattern.toRegex()) || Phones.length != 10 || Passwords.length > 0 && Passwords.length < 5 || CPasswords.length > 0 && CPasswords.length < 5
            ) {

                if (Fnames.length == 0) {
                    FirstName.requestFocus()
                    FirstName.error = "FIELD CANNOT BE EMPTY"
                } else if (Fnames.length != 0 && !Fnames.matches(namePattern.toRegex())) {
                    FirstName.requestFocus()
                    FirstName.error = "ENTER ONLY ALPHABETICAL CHARACTER"
                }
                if (Lnames.length == 0) {
                    LastName.requestFocus()
                    LastName.error = "FIELD CANNOT BE EMPTY"
                } else if (Lnames.length != 0 && !Lnames.matches(namePattern.toRegex())) {
                    LastName.requestFocus()
                    LastName.error = "ENTER ONLY ALPHABETICAL CHARACTER"
                }
                if (Emails.length == 0) {
                    Email.requestFocus()
                    Email.error = "FIELD CANNOT BE EMPTY"
                } else if (Emails.length != 0 && !Emails.matches(emailPattern.toRegex())) {
                    Email.requestFocus()
                    Email.error = "Invalid Email"
                }
                if (Passwords.length == 0) {
                    Password.requestFocus()
                    Password.error = "FIELD CANNOT BE EMPTY"
                }
                if (CPasswords.length == 0) {
                    CPassword.requestFocus()
                    CPassword.error = "FIELD CANNOT BE EMPTY"
                }
                if (isSelected_Gender == -1) {
                    tvGenderEr.visibility = View.VISIBLE
                    tvGenderEr.error = "Select Gender"
                    Male.setOnCheckedChangeListener(Radio_Check())
                }
                if (isSelected_Gender == -1) {
                    tvGenderEr.visibility = View.VISIBLE
                    tvGenderEr.error = "Select Gender"
                    Female.setOnCheckedChangeListener(Radio_Check())
                }
                if (isSelected_Chkbox == -1) {
                    tvChkboxEr.setVisibility(View.VISIBLE)
                    tvChkboxEr.setError(" ")
                    rbcheckbox.setOnCheckedChangeListener(Radio_Check())
                }
                if (Phones.length == 0) {
                    Phone.requestFocus()
                    Phone.setError("FIELD CANNOT BE EMPTY")
                } else if (Phones.length != 10) {
                    Phone.requestFocus()
                    Phone.setError("ENTER 10 DIGIT PHONE NO")
                }
                if (Passwords.length > 0 && Passwords.length < 6) {
                    Password.requestFocus()
                    Password.error = "Password Greater than 6 Digit"
                }
                if (CPasswords.length > 0 && CPasswords.length < 6) {
                    CPassword.requestFocus()
                    CPassword.error = "Password Greater than 6 Digit"
                }
            } else if (Passwords != CPasswords) {
                if (Passwords != CPasswords) {
                    CPassword.requestFocus()
                    CPassword.error = "Password MisMatch"
                }
            } else {
                if (Male.isChecked) {
                    println("---------------------------------------------$genders")
                    genders = Male.text.toString().substring(0, 1)
                    println("---------------------------------------------$genders")
                } else if (Female.isChecked) {
                    genders = Female.text.toString().substring(0, 1)
                    println("---------------------------------------------$genders")
                }
                println("-------------------------------------Data----------------------------------------")
                println("$Fnames  $Lnames  $Emails  $Passwords  $CPasswords  $genders  $Phones")
                viewModel.makeRegisterApiCall(Fnames,Lnames,Emails,Passwords,CPasswords,genders,Phones)
                println("---------------------------------Register-------------------------------------------")
            }
        })
    }
}