package com.example.lab1_sofiia

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class SuccessLoginMessage(message: String) : DialogFragment() {

    private val showedMessage: String = message

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Success!!!")
                .setMessage("You have successfully $showedMessage")
                .setPositiveButton("Close") {
                    dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
