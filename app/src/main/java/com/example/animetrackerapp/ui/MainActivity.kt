package com.example.animetrackerapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.animetrackerapp.R
import android.content.Context
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.example.animetrackerapp.data.AnimeSerie
import com.example.animetrackerapp.data.Estado
import com.example.animetrackerapp.data.Genero
import com.example.animetrackerapp.databinding.ActivityMainBinding
import com.example.animetrackerapp.viewmodel.AnimeViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinners()
        setupBotonRegistrar()
        validarAccesoALaColeccion()
        // Cargar desde SharedPreferences
        cargarSeriesGuardadas()?.forEach {
            viewModel.agregarSerie(it)
        }
    }
    override fun onResume() {
        super.onResume()
        validarAccesoALaColeccion()
    }

    private fun setupSpinners() {
        val generos = Genero.values().map { it.name }
        val estados = Estado.values().map { it.name }

        binding.spinnerGenero.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, generos)
        binding.spinnerEstado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estados)

    }

    private fun setupBotonRegistrar() {
        binding.btnRegistrar.setOnClickListener {
            try {
                val nombre = binding.etNombre.text.toString().trim()
                val anio = binding.etAnio.text.toString().trim().toIntOrNull()
                val comentario = binding.etComentario.text.toString().trim()
                val genero = Genero.valueOf(binding.spinnerGenero.selectedItem.toString())
                val valoracion = binding.sliderValoracion.value.toInt()
                val estado = Estado.valueOf(binding.spinnerEstado.selectedItem.toString())

                // Validaciones
                if (nombre.isEmpty() || comentario.isEmpty() || anio == null) {
                    showToast("Como diría Goku: ¡Concéntrate y rellena los campos!")
                    return@setOnClickListener
                }

                if (anio !in 1960..2025) {
                    showToast("¡Ese isekai es de otra linea temporal! Revisá el año.")
                    return@setOnClickListener
                }

                val serie = AnimeSerie(
                    nombre = nombre,
                    anio = anio,
                    comentario = comentario,
                    genero = genero,
                    valoracion = valoracion,
                    estado = estado
                )

                viewModel.agregarSerie(serie)
                guardarSerie(serie)

                showToast("¡Serie registrada! Naruto estaría orgulloso.")
                limpiarCampos()

                // Ir al Listado
                val intent = Intent(this, ListadoActivity::class.java)
                val listaSegura = viewModel.series.value ?: emptyList()
                intent.putExtra("series_json", Gson().toJson(listaSegura))
                startActivity(intent)


            } catch (e: Exception) {
                showToast("Algo falló como en el final de Evangelion: ${e.message}")
                e.printStackTrace() // 👈 Agregá esto
            }
        }
        binding.btnIrAListado.setOnClickListener {
            val intent = Intent(this, ListadoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun validarAccesoALaColeccion() {
        val sharedPref = getSharedPreferences("anime_pref", Context.MODE_PRIVATE)
        val json = sharedPref.getString("series", null)
        val tieneSeries = json != null && json != "[]" // 👈 importante este chequeo

        binding.btnIrAListado.isEnabled = tieneSeries
    }

    private fun limpiarCampos() {
        binding.etNombre.text?.clear()
        binding.etAnio.text?.clear()
        binding.etComentario.text?.clear()
        binding.sliderValoracion.value = 4f
        binding.spinnerGenero.setSelection(0)
        binding.spinnerEstado.setSelection(0)
    }

    private fun guardarSerie(serie: AnimeSerie) {
        val sharedPref = getSharedPreferences("anime_pref", Context.MODE_PRIVATE)
        val listaActual = cargarSeriesGuardadas()?.toMutableList() ?: mutableListOf()
        listaActual.add(serie)
        val json = Gson().toJson(listaActual)
        sharedPref.edit().putString("series", json).apply()
    }

    private fun cargarSeriesGuardadas(): List<AnimeSerie>? {
        val sharedPref = getSharedPreferences("anime_pref", Context.MODE_PRIVATE)
        val json = sharedPref.getString("series", null) ?: return null
        val type = object : TypeToken<List<AnimeSerie>>() {}.type
        return Gson().fromJson(json, type)
    }

}