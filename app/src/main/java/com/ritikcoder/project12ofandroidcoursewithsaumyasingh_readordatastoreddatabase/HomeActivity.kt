package com.ritikcoder.project12ofandroidcoursewithsaumyasingh_readordatastoreddatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val name= intent.getStringExtra(LoginActivity.keyName)
        val userName= intent.getStringExtra(LoginActivity.keyUserName)
        val email= intent.getStringExtra(LoginActivity.keyEmail)

        Toast.makeText(this, "$name, $userName", Toast.LENGTH_SHORT).show()

        val textViewWelcome: TextView= findViewById(R.id.textViewForWelcome)
        val textViewUserName: TextView= findViewById(R.id.textViewForUserName)
        val textViewEmail: TextView= findViewById(R.id.textViewForEmail)

        textViewWelcome.text= "Welcome, $name"
        textViewUserName.text= "UserName : $userName"
        textViewEmail.text= "Email : $email"

    }
}