    package com.example.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException


    class SignUp : AppCompatActivity() {

    // following items would be initialized later
    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnRegister = findViewById(R.id.btnRegister)

        // button to register user
        btnRegister.setOnClickListener{
            Toast.makeText(applicationContext, "Registering",Toast.LENGTH_SHORT).show()
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val age = edtAge.text.toString()

            if (email.trim().isNotEmpty() && password.trim().isNotEmpty() && age.trim().isNotEmpty()) {
                signUp(email, password)
            }
            // handling empty text fields
            else {
                if (name.trim().isEmpty())
                    edtName.error = "Name cannot be empty"
                if (email.trim().isEmpty())
                    edtEmail.error = "Email cannot be empty"
                if (password.trim().isEmpty())
                    edtPassword.error = "Password cannot be empty"
                if (age.trim().isEmpty()){
                    edtAge.error = "Age cannot be empty"
                }
            }
        }

    }


    private fun signUp(email: String, password: String) {
        // logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for jumping to home
                    val intent = Intent(this@SignUp, MainActivity:: class.java)
                    startActivity(intent)

                } else {
                    // display error message
                    Toast.makeText(this@SignUp, "Some error occured", Toast.LENGTH_SHORT).show()

                }
            }

    }



}