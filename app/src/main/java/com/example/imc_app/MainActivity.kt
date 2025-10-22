package com.example.imc_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import java.util.Objects

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val seekHeight: SeekBar = findViewById(R.id.sbHeight)
        val tvHeight: TextView = findViewById(R.id.tvHeight)

        val cvMale: CardView = findViewById(R.id.cvMale)
        val llMale: LinearLayout = findViewById(R.id.llMale)

        val cvFemale: CardView = findViewById(R.id.cvFemale)
        val llFemale: LinearLayout = findViewById(R.id.llFemale)

        val btnWeighAdd: Button = findViewById(R.id.btnWeightAdd)
        val btnWeightSub: Button = findViewById(R.id.btnWeightSub)
        val tvWeight: TextView = findViewById(R.id.tvWeightDefault)

        val btnAgeAdd: Button = findViewById(R.id.btnAgeAdd)
        val btnAgeSub: Button = findViewById(R.id.btnAgeSub)
        val tvAge: TextView = findViewById(R.id.tvAgeDefault)

        val btCalculate: Button = findViewById(R.id.btCalculate)

        var cardGender: String = ""

        cvMale.setOnClickListener {
            if (cardGender == "male") {
                cardGender = ""
                llMale.setBackgroundColor(resources.getColor(R.color.card, theme))
            } else {
                cardGender = "male"
                llMale.setBackgroundColor(resources.getColor(R.color.target, theme))
                llFemale.setBackgroundColor(resources.getColor(R.color.card, theme))
            }
        }
        cvFemale.setOnClickListener {
            if (cardGender == "female") {
                cardGender = ""
                llFemale.setBackgroundColor(resources.getColor(R.color.card, theme))
            } else {
                cardGender = "female"
                llFemale.setBackgroundColor(resources.getColor(R.color.target, theme))
                llMale.setBackgroundColor(resources.getColor(R.color.card, theme))
            }
        }

        var height = 70;
        seekHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvHeight.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                height = seekBar?.progress ?: 0
            }
        })
        var weight = 170
        btnWeighAdd.setOnClickListener {
            weight = suma(weight,true)
            tvWeight.text = weight.toString()
        }
        btnWeightSub.setOnClickListener {
            weight = suma(weight,false)
            tvWeight.text = weight.toString()
        }

        var age = 18
        btnAgeAdd.setOnClickListener {
            age = suma(age,true)
            tvAge.text = age.toString()
        }
        btnAgeSub.setOnClickListener {
            age = suma(age,false)
            tvAge.text = age.toString()
        }

        val imc: Float = (703f * weight) / (height * height)
        btCalculate.setOnClickListener {
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("imc",imc)
            startActivity(intent)
        }

    }

    fun suma(numero:Int, suma: Boolean): Int {
        if (suma) return numero + 1
        else{
           if (numero > 0) return numero - 1
        }
        return numero
    }
}