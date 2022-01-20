package com.example.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var profileImage: View

    private lateinit var mAuth : FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnRegister = findViewById(R.id.btnRegister)
        profileImage = findViewById(R.id.shapeableImageView)

        // button to register user
        btnRegister.setOnClickListener{
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val age = edtAge.text.toString()

            if (name.trim().isNotEmpty() &&  email.trim().isNotEmpty() && password.trim().isNotEmpty() && age.trim().isNotEmpty() && password.trim().length >=6 && age.trim().toInt() >=16) {
                signUp(name, email, password)
            }
            // handling empty text fields
            else {
                if (name.trim().isEmpty())
                    edtName.error = "Name cannot be empty"
                if (email.trim().isEmpty())
                    edtEmail.error = "Email cannot be empty"
                if (password.trim().isEmpty())
                    edtPassword.error = "Password cannot be empty"
                if(password.trim().length<6)
                    edtPassword.error = "Password must be at least 6 digits"
                if (age.trim().isEmpty())
                    edtAge.error = "Age cannot be empty"
                if(age.trim().toInt()<16)
                    edtAge.error = "Age must be bigger than 16"
            }
        }

    }

    // logic of creating user
    private fun signUp(name:String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    // if sign up successful, add user to database
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)

                    // code for jumping to home
                    val intent = Intent(this@SignUp, MainActivity:: class.java)
                    // finish current activity
                    finish()
                    startActivity(intent)
                }
                // display error message
                else {
                    Toast.makeText(this@SignUp, "Some error occurred", Toast.LENGTH_SHORT).show()

                }
            }

    }

    // self explanatory
    private fun addUserToDatabase(name: String, email: String, uid: String) {
        dbRef = FirebaseDatabase.getInstance().getReference()

        // child adds a node to the database
        // we create nodes using uid so that we have a unique node for every user
        dbRef.child("user").child(uid).setValue(User(name,email,uid))
    }


}