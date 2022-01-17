package com.example.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // initializing firebase authentication
        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnRegister)

        // opening sign up activity
        btnSignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        // log in button
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            if (email.trim().isNotEmpty() && password.trim().isNotEmpty())
                login(email, password)
            // handling empty text fields
            else {
                if (email.trim().isEmpty())
                    edtEmail.error = "Username cannot be empty"
                if (password.trim().isEmpty())
                    edtPassword.error = "Password cannot be empty"
            }
        }
    }
    private fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // if login successful, go to MainActivity
                    val intent = Intent(this@LogIn, MainActivity::class.java)
                    startActivity(intent);

                } else
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@LogIn, "User does not exist.", Toast.LENGTH_SHORT).show()
        }
    }
}