package com.example.chitchat

class message {
    var message: String? = null
    var senderID: String? = null

    constructor(){}

    constructor(message: String?, senderID: String?){
        this.message = message
        this.senderID = senderID

    }
}