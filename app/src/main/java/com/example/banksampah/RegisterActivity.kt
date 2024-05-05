package com.example.banksampah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.banksampah.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener {
            val email = binding.emailReg.text.toString()
            val password = binding.passwordReg.text.toString()
            val confirmPass =  binding.confirmPassReg.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()){
                if (password == confirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){ task->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show()
                            val intentLoginActivity = Intent(this, LoginActivity::class.java)
                            startActivity(intentLoginActivity)
                            finish()
                        }else{
                            Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Password does not matched", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLoginPg.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}