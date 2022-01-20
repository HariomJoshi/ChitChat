package com.example.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter

    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference()

        // code to handle changes to the database
        dbRef.child("user").addValueEventListener(object: ValueEventListener{

            // Any time you read data from the Database, you receive the data as a DataSnapshot
            // https://firebase.google.com/docs/reference/js/v8/firebase.database.DataSnapshot

            override fun onDataChange(snapshot: DataSnapshot) {

                // this clears the userList in case a new user is added and a new, updated
                // list is created using a for loop
                userList.clear()

                // getValue of every user in specified node(pathString) and add it to userList
                for(user in snapshot.children){
                    val currentUser = user.getValue(User::class.java)
                    userList.add(currentUser!!)
                }

                // notify adapter of new changes in data, so recyclerView can be updated
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        userList = ArrayList()
        adapter = UserAdapter(this, userList)

        userRecyclerView = findViewById(R.id.userRecyclerView)
        userRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)

        userRecyclerView.adapter = adapter
    }


    // functions for options menu

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // if user logs out
        if(item.itemId == R.id.logout) {
            // log user out
            mAuth.signOut()

            // open login screen again
            val intent = Intent(this@MainActivity,LogIn::class.java)
            finish()
            startActivity(intent)

            return true
        }
        return true
    }


}