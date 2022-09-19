package com.exampleone.addapp.data.database

import com.exampleone.addapp.data.model.Articles
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DbManager {
    private val db = Firebase.database.getReference("Main")

    fun publishArt(art: Articles) {
        db.child("art").setValue(art)
    }
}