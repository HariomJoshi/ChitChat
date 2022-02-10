package com.example.chitchat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context: Context, val userList: ArrayList<User>):
    RecyclerView.Adapter<UserAdapter .UserViewHolder>() {

    // returns the size of userList
    override fun getItemCount(): Int {
        return userList.size
    }

    //creating view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view)
    }

    // this updates the name_textview of all the views inside the recylerView
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.tvName.text = currentUser.name
        Glide.with(context)
            .load(currentUser.profileImage)
            .placeholder(R.drawable.avatar)
            .into(holder.profileImage)

        // start chatActivity on clicking user name
        holder.itemView.setOnClickListener{
            val intent = Intent(context, ChatActivity::class.java)

            // send these details to the chatActivity
            intent.putExtra("name", currentUser.name)
            intent.putExtra("uid", currentUser.uid)

            context.startActivity(intent)
        }
    }

    // view holder class for displaying users
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // here, itemView is user_layout.xml
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val profileImage: ShapeableImageView = itemView.findViewById(R.id.profileImage)
    }
}
