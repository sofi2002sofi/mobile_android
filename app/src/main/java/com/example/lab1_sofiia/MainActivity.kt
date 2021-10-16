package com.example.lab1_sofiia

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private var emailInput: TextInputEditText? = null
    private var passwordInput: TextInputEditText? = null
    private var emailInputLayout: TextInputLayout? = null
    private var passwordInputLayout: TextInputLayout? = null
    private var signInButton: Button? = null
    private var signUpLink: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        emailInputLayout = findViewById(R.id.email_layout)
        emailInput = findViewById(R.id.email_input)
        passwordInputLayout = findViewById(R.id.password_layout)
        passwordInput = findViewById(R.id.password_input)
        signInButton = findViewById(R.id.sign_in_button)
        signUpLink = findViewById(R.id.create_account_link)

        signUpLink?.setOnClickListener() {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        signInButton?.setOnClickListener() {
            if (validateInputs()) {
                val successResult = SuccessLoginMessage("logged in")
                val fragmentManager = supportFragmentManager
                successResult.show(fragmentManager, "SuccessLogIn_tag")
            }
        }
    }

    private fun validateInputs(): Boolean {
        var validationResult = true

        if (emailInput?.text.toString().isEmpty()) {
            emailInputLayout?.error = "The field can not be empty!"
            validationResult = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput?.text.toString()).matches()) {
            emailInputLayout?.error = "Wrong format of email"
            validationResult = false
        }

        if (passwordInput?.text.toString().isEmpty()) {
            passwordInputLayout?.error = "The field can not be empty!"
            validationResult = false
        } else if (passwordInput?.text.toString().length < 8) {
            passwordInputLayout?.error = "Password must contain more than 8 characters"
            validationResult = false
        }

        return validationResult
    }
}
