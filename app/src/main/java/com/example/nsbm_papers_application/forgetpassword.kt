package com.example.nsbm_papers_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class forgetpassword : AppCompatActivity() {
    private lateinit var send:Button
    private lateinit var email:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpassword)
        send=findViewById(R.id.send)
       email=findViewById(R.id.foremail)



        send.setOnClickListener {
            val email = email.text.toString().trim()
            if(email.isEmpty()){
                Toast.makeText(this@forgetpassword, "please enter the email",
                    Toast.LENGTH_SHORT).show()
            }else{

                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@forgetpassword,
                                "Email sent successflly to reset your password",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@forgetpassword, login::class.java) //this @classname
                            finish()
                            startActivity(intent)


                        }else{
                            Toast.makeText(
                                this@forgetpassword,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

            }

        }

    }
}