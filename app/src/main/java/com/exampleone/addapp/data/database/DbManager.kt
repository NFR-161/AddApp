package com.exampleone.addapp.data.database

import com.exampleone.addapp.data.model.Articles
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DbManager {

    val db = Firebase.database.getReference("Main")

    fun publishArtEasy(art: Articles) {
        db.child("artEasy").setValue(art.easyArt)
    }

    fun publishArtNormal(art: Articles) {
        db.child("artNormal").setValue(art.normalArt)
    }

    fun publishArtHard(art: Articles) {
        db.child("artHard").setValue(art.hardArt)
    }

}