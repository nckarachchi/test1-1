package com.example.nsbm_papers_application

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.malkinfo.progressbar.uitel.LoadingDialog

class login : AppCompatActivity() {

    private lateinit var edemail:EditText
    private lateinit var edpassword:EditText
    private lateinit var btlogin:Button
    private lateinit var btsignup:Button
    private lateinit var inster : ImageButton
    private lateinit var facebook : ImageButton
    private lateinit var twitter : ImageButton
    private lateinit var fpassword:TextView
    private lateinit var mauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
            }

        },5000)
        setContentView(R.layout.activity_login)
        mauth = FirebaseAuth.getInstance()
        edemail=findViewById(R.id.email)
        edpassword=findViewById(R.id.password)
        btlogin=findViewById(R.id.login)
        btsignup=findViewById(R.id.create)
        inster=findViewById(R.id.inster)
        twitter=findViewById(R.id.twitter)
        facebook=findViewById(R.id.facebook_btn)
        fpassword=findViewById(R.id.fPassword)

        edemail.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(edemail.text.toString()).matches()){
                }
                    else{
                        edemail.setError("invalid email")
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        fpassword.setOnClickListener {
            val intent =Intent(this@login, forgetpassword::class.java) //this @classname

            startActivity(intent)
        }
inster.setOnClickListener {
    val i =Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"))
    startActivity(i)
}
        twitter.setOnClickListener {
            val i =Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitter.com"))
            startActivity(i)
        }
        facebook.setOnClickListener {
            val i =Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"))
            startActivity(i)
        }
        btsignup.setOnClickListener {
            val intent = Intent(this@login,signup::class.java)
            startActivity(intent)
        }
        btlogin.setOnClickListener {
            val email = edemail.text.toString().trim()
            val password =edpassword.text.toString().trim()

            if(email.isEmpty()){
                Toast.makeText(this@login, "Please enter the Email,try again",
                    Toast.LENGTH_SHORT).show()
            }

            else if(password.isEmpty()){
                Toast.makeText(this@login, "Please enter the Password, try again",
                    Toast.LENGTH_SHORT).show()

            }
            else{
                Login(email,password);
            }
        }

    }
    private fun Login(email : String , password :   String)
    {
        mauth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent =Intent(this@login, MainActivity::class.java) //this @classname
                    finish()
                    startActivity(intent)


                }


                else {
                    Toast.makeText(this@login,"user dose not exist ,try again... ", Toast.LENGTH_SHORT).show()
                }
            }
    }
}