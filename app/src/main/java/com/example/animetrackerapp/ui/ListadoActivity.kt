package com.example.animetrackerapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animetrackerapp.adapter.AnimeAdapter
import com.example.animetrackerapp.data.AnimeSerie
import com.example.animetrackerapp.databinding.ActivityListadoBinding
import com.example.animetrackerapp.viewmodel.AnimeViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadoBinding
    private val viewModel: AnimeViewModel by viewModels()
    private lateinit var adapter: AnimeAdapter
    private var listaOriginal: MutableList<AnimeSerie> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarListaDesdePrefs()
        inicializarRecyclerView()
        configurarBuscador()
        configurarFiltro()
    }
    override fun onResume() {
        super.onResume()
        cargarListaDesdePrefs()
    }

    private fun cargarListaDesdePrefs() {
        val sharedPref = getSharedPreferences("anime_pref", Context.MODE_PRIVATE)
        val json = sharedPref.getString("series", null)
        if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<MutableList<AnimeSerie>>() {}.type
            val lista = Gson().fromJson<MutableList<AnimeSerie>>(json, type)
            listaOriginal = lista

            if (this::adapter.isInitialized) {
                adapter.actualizarLista(listaOriginal)
            }
        }
    }


    private fun inicializarRecyclerView() {
        adapter = AnimeAdapter(listaOriginal,
            onEditar = { serie ->
                val intent = Intent(this, EditarActivity::class.java)
                intent.putExtra("serie", serie)
                startActivity(intent)
            },
            onEliminar = { serie ->
                listaOriginal.remove(serie)
                guardarCambios()
                adapter.actualizarLista(listaOriginal)
            })

        binding.recyclerSeries.layoutManager = LinearLayoutManager(this)
        binding.recyclerSeries.adapter = adapter
    }

    private fun configurarBuscador() {
        binding.etBuscar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val texto = s.toString().lowercase()
                val filtrada = listaOriginal.filter {
                    it.nombre.lowercase().contains(texto)
                }
                adapter.actualizarLista(filtrada)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun configurarFiltro() {
        val opciones = listOf("Sin orden", "Por Año", "Por Valoración")
        binding.spinnerFiltro.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)

        binding.spinnerFiltro.setOnItemSelectedListener { _, _, pos, _ ->
            val ordenada = when (pos) {
                1 -> listaOriginal.sortedBy { it.anio }
                2 -> listaOriginal.sortedByDescending { it.valoracion }
                else -> listaOriginal
            }
            adapter.actualizarLista(ordenada)
        }
    }

    private fun guardarCambios() {
        val sharedPref = getSharedPreferences("anime_pref", Context.MODE_PRIVATE)
        val json = Gson().toJson(listaOriginal)
        sharedPref.edit().putString("series", json).apply()
    }

    // Compatibilidad para spinner.setOnItemSelectedListener con lambda
    private fun Spinner.setOnItemSelectedListener(listener: (parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) -> Unit) {
        this.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                listener(parent, view, position, id)
            }
            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {}
        }
    }
}
