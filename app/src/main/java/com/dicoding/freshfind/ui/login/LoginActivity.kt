package com.dicoding.freshfind.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.freshfind.MainActivity
import com.dicoding.freshfind.R
import com.dicoding.freshfind.ui.home.HomeFragment
import com.dicoding.freshfind.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {
            val moveIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(moveIntent)
        }
        val registerLogin = findViewById<TextView>(R.id.login_register)
        registerLogin.setOnClickListener {
            val moveIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(moveIntent)
        }

    }
}