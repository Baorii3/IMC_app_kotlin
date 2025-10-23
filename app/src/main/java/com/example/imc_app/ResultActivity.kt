package com.example.imc_app

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    lateinit var tvResult: TextView
    lateinit var tvDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        val intent = this.intent
        val imc = intent.getFloatExtra("imc",0f)
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.navigationIcon?.setTint(resources.getColor(R.color.white, theme))

        tvResult = findViewById(R.id.tvResult)
        val tvImc: TextView = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)

        tvImc.text = String.format("%.2f", imc)
        textResult(imc)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    fun textResult(imc: Float) {
        when {
            imc >= 30 -> {
                tvResult.text = getString(R.string.obesidad)
                tvResult.setTextColor(resources.getColor(R.color.red, theme))
                tvDescription.text = getString(R.string.obesidad)
            }

            imc >= 25 -> {
                tvResult.text = getString(R.string.sobrepeso)
                tvResult.setTextColor(resources.getColor(R.color.orange, theme))
                tvDescription.text = getString(R.string.sobrepeso)
            }
            imc >= 18.5 -> {
                tvResult.text = getString(R.string.normal)
                tvResult.setTextColor(resources.getColor(R.color.green, theme))
                tvDescription.text = getString(R.string.normal)

            }
            imc < 18.5 -> {
                tvResult.text = getString(R.string.bajo_peso)
                tvResult.setTextColor(resources.getColor(R.color.blue, theme))
                tvDescription.text = getString(R.string.bajo_peso)

            }
        }
    }

}