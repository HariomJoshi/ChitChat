package com.example.chitchat

class User {
    var name: String? = null
    var email: String? = null
    var uid: String? = null
    var profileImage: String? = null

    constructor(){}

    constructor(name: String? , email: String?, uid: String?){
        this.name = name
        this.email = email
        this.uid = uid
//        this.profileImage = profileImage
    }
}