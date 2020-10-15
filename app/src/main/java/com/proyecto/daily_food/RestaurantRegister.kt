package com.proyecto.daily_food

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_auth.emailEditText
import kotlinx.android.synthetic.main.activity_auth.paswordEditText
import kotlinx.android.synthetic.main.activity_restaurant_register.*
import kotlinx.android.synthetic.main.activity_restaurant_register.registerButton as registerButton1

class RestaurantRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_register)

        setUp()
    }

    private fun showHomeRestaurantes(){
        val homeRestaurantesIntent = Intent(this, HomeRestaurantes::class.java)
        startActivity(homeRestaurantesIntent)
    }

    private fun setUp(){

        title = "Regitro restaurante"

        registerButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && paswordEditText.text.isNotEmpty() && restaurantNameEditText.text.isNotEmpty()
                && editTextCiudad.text.isNotEmpty() && editTextPostalAddress.text.isNotEmpty() &&
                    editTextRestaurantType.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString()
                    , paswordEditText.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        DB().restaurantInfo(emailEditText?.text?.toString()?:"",
                            restaurantNameEditText?.text?.toString()?:"",
                            editTextCiudad?.text?.toString()?:"",
                            editTextPostalAddress?.text?.toString()?:"",
                            editTextRestaurantType?.text?.toString()?:""
                            )
                        showHomeRestaurantes()
                    }else{
                        AuthActivity().showAlert()
                    }
                }

            }
            else {
                AuthActivity().showAlert()
            }
        }

    }
}