package com.example.myapplicationxml

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplicationxml.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var edad: Double = 0.0
        var peso: Double = 0.0
        var altura: Double = 0.0
        var caloriasBasales: Double = 0.0
        var caloriasTotales: Double = 0.0


        fun calcularHidratos(): Int {

            val hidratosTotales = (caloriasTotales - ((peso * 2 * 4) + (peso * 9))) / 4
            return hidratosTotales.toInt()

        }

        fun calcularHidratosExtrema(): Int {

            var caloriasTotalesExtrema = caloriasTotales - 1000
            val hidratosTotales = (caloriasTotalesExtrema - ((peso * 3 * 4) + (peso * 4.5))) / 4
            return hidratosTotales.toInt()

        }



        binding.botonCalcular.setOnClickListener {
            var exception = 0

            try {
                edad = binding.textoEdad.text.toString().toDouble()
                peso = binding.textoPeso.text.toString().toDouble()
                altura = binding.textoAltura.text.toString().toDouble()

            } catch (e: NumberFormatException) {
                exception = 1
                Toast.makeText(this, "No puede haber campos vacios", Toast.LENGTH_LONG).show()
            }


            var proteinas = peso * 2
            var grasas = peso
            var hidratos = 0


//calorias basales

            if (exception == 0) {
                if (binding.radioButtonHombre.isChecked) {

                    caloriasBasales = (66.5 + (13.75 * peso) + (5.003 * altura) - (6.75 + edad))

                } else {

                    caloriasBasales = 655.1 + (9.563 * peso) + (1.85 * altura) - (4.676 * edad)

                }

                binding.textViewCaloriasDiarias.text = Editable.Factory.getInstance()
                    .newEditable("${caloriasBasales.toInt().toString()} CALORIAS BASALES")
//Calorias totales

                if (binding.radioButtonPoco.isChecked) {

                    caloriasTotales = caloriasBasales * 1.2

                } else if (binding.radioButtonLigero.isChecked) {

                    caloriasTotales = caloriasBasales * 1.375
                } else if (binding.radioButtonModerado.isChecked) {

                    caloriasTotales = caloriasBasales * 1.55
                } else if (binding.radioButtonFuerte.isChecked) {

                    caloriasTotales = caloriasBasales * 1.725
                } else {

                    caloriasTotales = caloriasBasales * 1.9
                }




                if (binding.radioButtonDefinicion.isChecked) {

                    hidratos = calcularHidratos() - 125
                    caloriasTotales = caloriasTotales - 500

                } else if (binding.radioButtonVolumen.isChecked) {

                    hidratos = calcularHidratos() + 125
                    caloriasTotales = caloriasTotales + 500
                } else if (binding.radioButtonMantenimiento.isChecked) {

                    hidratos = calcularHidratos()
                } else if (binding.radioButtonDfExtrema.isChecked) {

                    grasas = peso * 0.5
                    proteinas = peso * 3
                    caloriasTotales = caloriasTotales - 1000
                    hidratos = calcularHidratos()
                }
//proteinas
                binding.textViewProtein.text = Editable.Factory.getInstance()
                    .newEditable("${proteinas.toInt().toString()} gr PROTEINAS")
//grasas
                binding.textViewGrasa.text = Editable.Factory.getInstance()
                    .newEditable("${grasas.toInt().toString()} gr GRASAS")
//hidratos
                binding.textViewHidratos.text = Editable.Factory.getInstance()
                    .newEditable("${hidratos.toString()} gr HIDRATOS")

                //Calorias totales
                binding.textViewCaloriasTotales.text = Editable.Factory.getInstance()
                    .newEditable("${caloriasTotales.toInt().toString()} CALORIAS TOTALES")
            }
            //            else exception = 0
        }

    }
}


