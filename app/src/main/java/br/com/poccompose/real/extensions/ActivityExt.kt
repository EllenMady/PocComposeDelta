package br.com.poccompose.real.extensions

import android.app.AlertDialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



fun AppCompatActivity.showAlert(){

    val builder = AlertDialog.Builder(this)
    builder.setTitle("Androidly Alert")
    builder.setMessage("We have a message")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
        Toast.makeText(applicationContext,
            android.R.string.yes, Toast.LENGTH_SHORT).show()
    }

    builder.setNegativeButton(android.R.string.no) { dialog, which ->
        Toast.makeText(applicationContext,
            android.R.string.no, Toast.LENGTH_SHORT).show()
    }

    builder.setNeutralButton("Maybe") { dialog, which ->
        Toast.makeText(applicationContext,
            "Maybe", Toast.LENGTH_SHORT).show()
    }
    builder.show()
}