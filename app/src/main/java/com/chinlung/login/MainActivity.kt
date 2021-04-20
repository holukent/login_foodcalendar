package com.chinlung.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chinlung.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var wrongcount = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, foodcalendar::class.java)

            val account = binding.editAccount.text.toString()
            val password = binding.editPassword.text.toString()
            when  {
                account == "a" && password == "1"-> {
                    val bundle = Bundle()
                    bundle.putString("Account",account)
                    bundle.putString("Password",password)
                    intent.putExtra("bundle",bundle)
                    startActivity(intent)
                }
                account == "b" && password == "1"-> {
                    intent.putExtra("bundle",Bundle().also {
                        it.putString("Account",binding.editAccount.text.toString())
                        it.putString("Password",binding.editPassword.text.toString())
                        intent.putExtra("bundle",it)
                    })
                    startActivity(intent)
                }
                else -> {
                    wrongcount--
                    if (wrongcount == 0) binding.btnLogin.isEnabled = false
                    Toast.makeText(this, "帳號密碼錯誤 剩餘次數${wrongcount}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnCancel.setOnClickListener { finish() }


    }
}