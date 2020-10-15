package com.proyecto.daily_food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_auth.emailEditText
import kotlinx.android.synthetic.main.activity_auth.googleButton
import kotlinx.android.synthetic.main.activity_auth.logInButton
import kotlinx.android.synthetic.main.activity_auth.paswordEditText
import kotlinx.android.synthetic.main.activity_auth.registerButton
import kotlinx.android.synthetic.main.activity_login_restaurantes.*

class LoginRestaurantes : AppCompatActivity() {
    fun showAlert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_restaurantes)

        setUp()
    }
    private fun showHomeRestaurantes() {
        val homeRestaurantesIntent = Intent(this, HomeRestaurantes::class.java)
        startActivity(homeRestaurantesIntent)
    }

    private fun showRestaurantRegister(){
        val restaurantRegisterIntent = Intent(this, RestaurantRegister::class.java)
        startActivity(restaurantRegisterIntent)
    }

    private fun setUp() {

        title = "Login Restaurantes"

        registerButton.setOnClickListener {
            showRestaurantRegister()
        }

        logInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && paswordEditText.text.isNotEmpty() ) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    emailEditText.text.toString()
                    , paswordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHomeRestaurantes()
                    } else {
                        showAlert()
                    }
                }
            } else {
                showAlert()
            }
        }
    }
}