package com.proyecto.daily_food

import android.provider.ContactsContract
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class DB {
    private val db = FirebaseFirestore.getInstance()

    fun restaurantInfo(email:String, nombre:String, ciudad:String, direccion:String, tipoRestaurante:String){
        db.collection("restaurantes").document(email).set(
            hashMapOf("nombre" to nombre,
                "ciudad" to ciudad,
                "direccion" to direccion,
                "tipoRestaurante" to tipoRestaurante)
        )
    }

}