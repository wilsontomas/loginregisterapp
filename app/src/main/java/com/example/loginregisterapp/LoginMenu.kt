package com.example.loginregisterapp

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult

import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnCompleteListener

import android.R.attr.password







class LoginMenu : AppCompatActivity() {
    private lateinit var nombre:String;
    private lateinit var clave:String;
    private lateinit var loginBtn:Button;
    private lateinit var firebaseAuth:FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_menu);
        loginBtn = findViewById(R.id.loginBtnAction);
        loginBtn.setOnClickListener {
            logIn();
        }
        firebaseAuth = FirebaseAuth.getInstance();
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this,MainMenu::class.java);
            startActivity(intent);
        }
    }
    private fun validateCredential():Boolean{
        val nombreRef = findViewById<TextView>(R.id.loginUserName);
        nombre = nombreRef.text.toString().trim();

        val claveRef = findViewById<TextView>(R.id.loginPassword);
        clave = claveRef.text.toString().trim();
        if(nombre.isEmpty() || clave.isEmpty()){
            Toast.makeText(this,"Se deben llenar todos los campos",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private fun logIn(){
    if(validateCredential()){

  firebaseAuth.signInWithEmailAndPassword("$nombre/@gmail.com", clave)
    .addOnCompleteListener(this,OnCompleteListener { task ->
            if (task.isSuccessful) {
               // val user = firebaseAuth.currentUser;

                val intent = Intent(this,MainMenu::class.java);
                startActivity(intent);
            } else {
                Toast.makeText(
                    this, "Usuario o clave incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

}
}