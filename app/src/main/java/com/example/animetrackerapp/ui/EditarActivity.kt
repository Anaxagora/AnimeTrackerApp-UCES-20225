package com.example.animetrackerapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.animetrackerapp.data.AnimeSerie
import com.example.animetrackerapp.data.Estado
import com.example.animetrackerapp.data.Genero
import com.example.animetrackerapp.databinding.ActivityEditarBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EditarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarBinding
    private lateinit var serieOriginal: AnimeSerie
    private var lista: MutableList<AnimeSerie> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarSpinners()
        obtenerSerie()
        cargarDatosEnPantalla()
        cargarListaDesdePrefs()
        configurarBotonActualizar()
    }

    private fun cargarSpinners() {
        val generos = Genero.values().map { it.name }
        val estados = Estado.values().map { it.name }

        binding.spinnerGeneroEditar.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, generos)
        binding.spinnerEstadoEditar.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estados)
    }

    private fun obtenerSerie() {
        serieOriginal = intent.getSerializableExtra("serie") as AnimeSerie
    }

    private fun cargarDatosEnPantalla() {
        binding.etNombreEditar.setText(serieOriginal.nombre)
        binding.etAnioEditar.setText(serieOriginal.anio.toString())
        binding.etComentarioEditar.setText(serieOriginal.comentario)
        binding.sliderValoracionEditar.value = serieOriginal.valoracion.toFloat()
        binding.spinnerGeneroEditar.setSelection(Genero.values().indexOf(serieOriginal.genero))
        binding.spinnerEstadoEditar.setSelection(Estado.values().indexOf(serieOriginal.estado))
    }

    private fun cargarListaDesdePrefs() {
        val sharedPref = getSharedPreferences("anime_pref", Context.MODE_PRIVATE)
        val json = sharedPref.getString("series", null)
        if (json != null) {
            val type = object : TypeToken<List<AnimeSerie>>() {}.type
            lista = Gson().fromJson(json, type)
        }
    }

    private fun configurarBotonActualizar() {
        binding.btnActualizar.setOnClickListener {
            val nuevoNombre = binding.etNombreEditar.text.toString().trim()
            val nuevoAnio = binding.etAnioEditar.text.toString().toIntOrNull()
            val nuevoComentario = binding.etComentarioEditar.text.toString().trim()
            val nuevoGenero = Genero.valueOf(binding.spinnerGeneroEditar.selectedItem.toString())
            val nuevoEstado = Estado.valueOf(binding.spinnerEstadoEditar.selectedItem.toString())
            val nuevaValoracion = binding.sliderValoracionEditar.value.toInt()

            if (nuevoNombre.isEmpty() || nuevoComentario.isEmpty() || nuevoAnio == null) {
                Toast.makeText(this, "¡No podés dejar campos vacíos! Hasta Light Yagami se equivocaría así.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (nuevoAnio !in 1960..2025) {
                Toast.makeText(this, "¿Anime del futuro? Revisá el año", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Buscar y reemplazar
            val index = lista.indexOfFirst { it.id == serieOriginal.id }
            if (index != -1) {
                lista[index] = serieOriginal.copy(
                    nombre = nuevoNombre,
                    anio = nuevoAnio,
                    comentario = nuevoComentario,
                    genero = nuevoGenero,
                    estado = nuevoEstado,
                    valoracion = nuevaValoracion
                )
                val json = Gson().toJson(lista)
                val sharedPref = getSharedPreferences("anime_pref", Context.MODE_PRIVATE)
                sharedPref.edit().putString("series", json).apply()
                val intent = Intent(this, ListadoActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "No se encontró la serie 😢", Toast.LENGTH_SHORT).show()
            }
        }
    }
}