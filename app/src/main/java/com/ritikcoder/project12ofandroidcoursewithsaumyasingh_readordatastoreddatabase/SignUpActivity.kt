package com.ritikcoder.project12ofandroidcoursewithsaumyasingh_readordatastoreddatabase

import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    //variable creation of Database Reference.
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //data collection into the Fields.
        val firstName: TextInputEditText= findViewById(R.id.textViewEditTextForFirstName)
        val lastName: TextInputEditText= findViewById(R.id.textViewEditTextForLastName)
        val userName: TextInputEditText= findViewById(R.id.textViewEditTextForUserName)
        val password: TextInputEditText= findViewById(R.id.textViewEditTextForPassword)
        val email: TextInputEditText= findViewById(R.id.textViewEditTextForEmail)
        val imageViewFaceBook: ImageView= findViewById(R.id.imageViewForFacebook)
        val imageViewGoogle: ImageView= findViewById(R.id.imageViewForGoogle)
        val imageViewTwitter: ImageView= findViewById(R.id.imageViewForTwitter)
        val linearLayoutOpenLoginActivity: LinearLayout= findViewById(R.id.linearLayoutToOpenLoginActivity)
        val buttonSignUp: Button= findViewById(R.id.buttonForSignUp)

        linearLayoutOpenLoginActivity.setOnClickListener(){
            val intentToOpenLoginActivity= Intent(this, LoginActivity::class.java)
            startActivity(intentToOpenLoginActivity)
        }

        //Click listener to SignUp button
        buttonSignUp.setOnClickListener() {
            val name= firstName.text.toString()+ " "+ lastName.text.toString()
            val userName1= userName.text.toString()
            val password1= password.text.toString()
            val email1= email.text.toString()

            database= FirebaseDatabase.getInstance().getReference("Users")
            database.child(userName1).get().addOnSuccessListener {
                //getting userName from the database to check existing user or not.
                val userNameDatabase= it.child("userName").value

                //checking the condition is there user is already registered or not.
                if(userNameDatabase== userName1){
                    Toast.makeText(this, "$userName1 : is already registered", Toast.LENGTH_SHORT).show()
                }else{
                    val user= UserKotlinClass(name, userName1, password1, email1)
                    database= FirebaseDatabase.getInstance().getReference("Users")
                    database.child(userName1).setValue(user).addOnSuccessListener {

                        //Cleaning all fields.
                        firstName.text?.clear()
                        lastName.text?.clear()
                        userName.text?.clear()
                        password.text?.clear()
                        email.text?.clear()
                        
                        Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()
                    }

                }
            }.addOnFailureListener {
                Toast.makeText(this, "OOPs can't connect with the database", Toast.LENGTH_SHORT).show()
            }
        }

        //Click listener to Facebook Image
        imageViewFaceBook.setOnClickListener(){
            val intentToOpenFacebook= Intent(Intent.ACTION_VIEW)
            intentToOpenFacebook.data= Uri.parse("https://www.facebook.com/")
            startActivity(intentToOpenFacebook)

        }

        //Click listener to Google image
        imageViewGoogle.setOnClickListener(){
            val intentToOpenGoogle= Intent(Intent.ACTION_VIEW)
            intentToOpenGoogle.data= Uri.parse("https://accounts.google.com/")
            startActivity(intentToOpenGoogle)

        }

        //Click listener to Twitter image
        imageViewTwitter.setOnClickListener(){
            val intentToOpenTwitter= Intent(Intent.ACTION_VIEW)
            intentToOpenTwitter.data= Uri.parse("https://twitter.com/")
            startActivity(intentToOpenTwitter)

        }

    }
}