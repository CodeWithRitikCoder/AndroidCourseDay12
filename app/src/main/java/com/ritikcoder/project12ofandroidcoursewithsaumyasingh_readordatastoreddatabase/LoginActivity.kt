package com.ritikcoder.project12ofandroidcoursewithsaumyasingh_readordatastoreddatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ritikcoder.project12ofandroidcoursewithsaumyasingh_readordatastoreddatabase.databinding.ActivitySignUpBinding

class LoginActivity : AppCompatActivity() {

    //Global DatabaseReference variable creation.
    private lateinit var databaseReference: DatabaseReference

    //creating global key
    companion object {
        const val keyEmail= "com.ritikcoder.project12ofandroidcoursewithsaumyasingh_readordatastoreddatabase.LoginActivity.keyEmail"
        const val keyName= "com.ritikcoder.project12ofandroidcoursewithsaumyasingh_readordatastoreddatabase.LoginActivity.keyName"
        const val keyUserName= "com.ritikcoder.project12ofandroidcoursewithsaumyasingh_readordatastoreddatabase.LoginActivity.keyUserName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //opening the Sign Up Activity.
        val linearLayout1: LinearLayout= findViewById(R.id.linearLayout2)
        linearLayout1.setOnClickListener(){
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val textInputEditTextLoginUserName: TextInputEditText= findViewById(R.id.textInputEditTextForLoginUserName)
        val textInputEditTextLoginPassword: TextInputEditText= findViewById(R.id.textInputEditTextForLoginPassword)
        val buttonLogin: Button= findViewById(R.id.buttonForLogin)

        buttonLogin.setOnClickListener(){
            //getting data into the UserName field.
            val userName= textInputEditTextLoginUserName.text.toString()
            val password= textInputEditTextLoginPassword.text.toString()

            //checking is UserName field is Empty or not.
            if(userName.isNotEmpty() && password.isNotEmpty()){
                readData(userName, password)

            }else{
                Toast.makeText(this, "Please Enter User Name", Toast.LENGTH_SHORT).show()

            }
        }

    }

    //User defined readData method creation is here.
    private fun readData(userName: String, password: String){
        Toast.makeText(this, "I'm in the readData Method", Toast.LENGTH_SHORT).show()
        databaseReference= FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userName).get().addOnSuccessListener {

            //getting password for the database to check some condition.
            val passwordOfDatabase= it.child("password").value

            //checking is there entered user is exist or not in the database.
            if(it.exists() && passwordOfDatabase== password){
                //getting data from the database.
                val emailOfDatabase= it.child("email").value
                val nameOfDatabase= it.child("name").value
                val userNameOfDatabase= it.child("userName").value
                Toast.makeText(this, "Connected get data successfully $nameOfDatabase, $userNameOfDatabase, $emailOfDatabase", Toast.LENGTH_SHORT).show()

                //Opening the welcome Activity with passing User name for user after login.
                val intentToOpenHomeActivity= Intent(this, HomeActivity::class.java)
                intentToOpenHomeActivity.putExtra(keyName, nameOfDatabase.toString())
                intentToOpenHomeActivity.putExtra(keyUserName, userNameOfDatabase.toString())
                intentToOpenHomeActivity.putExtra(keyEmail, emailOfDatabase.toString())
                startActivity(intentToOpenHomeActivity)

            }else{
                Toast.makeText(this, "User doesn't exist Sign in First..", Toast.LENGTH_SHORT).show()

            }

        }.addOnFailureListener {
            Toast.makeText(this, "Unable to connect with the database..", Toast.LENGTH_SHORT).show()

        }

    }

}