package com.example.loginregisterapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser




class MainMenu : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth;
    private lateinit var logOutBtn:Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        firebaseAuth = FirebaseAuth.getInstance();
        logOutBtn= findViewById(R.id.logOutBtn);

        logOutBtn.setOnClickListener {
            logOut();
        }
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = firebaseAuth.currentUser;
        if(currentUser==null){
            val intent = Intent(this,LoginMenu::class.java);
            startActivity(intent);
        }

    }

    private fun logOut(){
        firebaseAuth.signOut();
        val intent = Intent(this,LoginMenu::class.java);
        startActivity(intent);
    }
}