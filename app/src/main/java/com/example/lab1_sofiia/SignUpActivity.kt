package com.example.lab1_sofiia

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    private var backImageButton: ImageButton? = null
    private var emailInputLayout: TextInputLayout? = null
    private var emailInput: TextInputEditText? = null
    private var passwordInputLayout: TextInputLayout? = null
    private var passwordInput: TextInputEditText? = null
    private var confirmPasswordInputLayout: TextInputLayout? = null
    private var confirmPasswordInput: TextInputEditText? = null
    private var signUpButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        backImageButton = findViewById(R.id.back_button)
        emailInputLayout = findViewById(R.id.email_layout)
        emailInput = findViewById(R.id.email_input)
        passwordInputLayout = findViewById(R.id.password_layout)
        passwordInput = findViewById(R.id.password_input)
        confirmPasswordInputLayout = findViewById(R.id.confirm_password_layout)
        confirmPasswordInput = findViewById(R.id.confirm_password_input)
        signUpButton = findViewById(R.id.sign_up_button)

        backImageButton?.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
        }

        signUpButton?.setOnClickListener() {
            if (validateInputs()) {
                val successResult = SuccessLoginMessage("created an account")
                val fragmentManager = supportFragmentManager
                successResult.show(fragmentManager, "SuccessSignUp_tag")
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

        if (confirmPasswordInput?.text.toString().isEmpty()) {
            confirmPasswordInputLayout?.error = "The field can not be empty!"
            validationResult = false
        } else if (!confirmPasswordInput?.text.toString().equals(passwordInput?.text.toString())) {
            confirmPasswordInputLayout?.error = "Passwords do not match"
            validationResult = false
        }

        return validationResult
    }
}
