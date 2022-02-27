package com.example.nsbm_papers_application

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class signup : AppCompatActivity() {

    private lateinit var edtfistname : EditText
    private lateinit var edtsecondname : EditText
    private lateinit var edtemail: EditText
    private lateinit var edtbatchno: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnsignup: Button
    private lateinit var mauth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()
        mauth= FirebaseAuth.getInstance()
        edtfistname=findViewById(R.id.fristname)
        edtsecondname=findViewById(R.id.secondname)
        edtemail=findViewById(R.id.email)
        edtpassword=findViewById(R.id.password)
        edtbatchno=findViewById(R.id.batchno)
        btnsignup=findViewById(R.id.signup)







        edtemail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(Patterns.EMAIL_ADDRESS.matcher(edtemail.text.toString()).matches()){
                }
                else{
                    edtemail.setError("invalid email")
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })


        btnsignup.setOnClickListener {
            val name =edtfistname.text.toString()
            val lastname =edtsecondname.text.toString()
            val email= edtemail.text.toString()
            val batch= edtbatchno.text.toString()
            val password= edtpassword.text.toString()
            if(name.isEmpty()){
                edtfistname.error="Fill the first name correctly "
                return@setOnClickListener
            }
            else if(lastname.isEmpty()){
                edtsecondname.error="Fill the lastname correctly "
                return@setOnClickListener
            }

            else if(email.isEmpty()){
                edtemail.error="Fill the Email correctly "
                return@setOnClickListener

            }

            else if(batch.isEmpty()){
                edtbatchno.error="Fill the batch number correctly"
                return@setOnClickListener

            }
            else if(password.isEmpty()){
                edtpassword.error="Fill the password correctly"
                return@setOnClickListener

            }
            else{
                signUp(name,lastname,email,batch,password)
            }




        }
    }
    private fun signUp(fristname:String ,lastname:String,email:String ,batch: String,password:String){ //use above  signUp
        mauth.createUserWithEmailAndPassword(email, password)

            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    ///code for jumping to home

                    val intent =Intent(this@signup, MainActivity::class.java) //this @classname
                    startActivity(intent)

                    addUserToDatabase(fristname,lastname,email,batch,password,mauth.currentUser?.uid!! )

                    finish()
                } else {
                    Toast.makeText(this@signup,"some error occurred ",Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun addUserToDatabase(fristname: String,lastname: String,email: String,batch: String,password: String,uid:String) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid)
            .setValue(user(fristname, lastname, email, batch, password, uid))

    }

    }


