package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class MainActivity : AppCompatActivity() {

    var colors = arrayOf(0xC5CAE9,0xC5CAE9,0xC5CAE9)
    var sheetN = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sheetN = getIntent().getIntExtra("sheetN", 0)
        val next = findViewById<Button>(R.id.button)
        val textEd = findViewById<EditText>(R.id.editTextText)
        next.setOnClickListener {
            if (sheetN < colors.size - 1) {
                val Intent = Intent(this, this::class.java)
                Intent.putExtra("sheetN", sheetN + 1)
                startActivity(Intent)
            } else {
                Toast.makeText(applicationContext, getString(R.string.textEd), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

        override fun onPause(){
            super.onPause()
            val textEd = findViewById<EditText>(R.id.editTextText)
            val prefs :SharedPreferences.Editor = getPreferences(Context.MODE_PRIVATE).edit()
            prefs.putString("note"+sheetN,  textEd.editableText.toString())
            prefs.apply()
        }

        override fun onResume() {
            super.onResume()
            val textEd = findViewById<EditText>(R.id.editTextText)
            val Sheet = findViewById<ConstraintLayout>(R.id.sheet)
            Sheet.setBackgroundColor(colors[sheetN].toInt())
            val SaveState = getPreferences(Context.MODE_PRIVATE).getString("note"+ sheetN.toString(),null)
            if (SaveState == null){

            }
        }

}