    package com.example.chitchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


    class SignUp : AppCompatActivity() {

    // following items would be initialized later
    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnRegister = findViewById(R.id.btnRegister)

        // button to register user
        btnRegister.setOnClickListener{
            Toast.makeText(applicationContext, "Registering",Toast.LENGTH_SHORT).show();
        }
    }

}