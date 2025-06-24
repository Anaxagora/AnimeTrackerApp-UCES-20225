package com.example.animetrackerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animetrackerapp.data.AnimeSerie
import com.example.animetrackerapp.databinding.ItemSerieBinding

class AnimeAdapter(
    private var lista: List<AnimeSerie>,
    private val onEditar: (AnimeSerie) -> Unit,
    private val onEliminar: (AnimeSerie) -> Unit
) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    inner class AnimeViewHolder(val binding: ItemSerieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = ItemSerieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val serie = lista[position]
        with(holder.binding) {
            tvNombre.text = serie.nombre
            tvAnio.text = "Año: ${serie.anio}"
            tvComentario.text = serie.comentario
            tvValoracion.text = "Valoración: ${serie.valoracion}/7"
            tvGenero.text = serie.genero.name
            tvEstado.text = serie.estado.name

            // Estilos visuales (colores distintos por género y estado)
            tvGenero.setBackgroundColor(obtenerColorPorGenero(serie.genero.name))
            tvEstado.setBackgroundColor(obtenerColorPorEstado(serie.estado.name))

            btnEditar.setOnClickListener {
                onEditar(serie)
            }

            btnEliminar.setOnClickListener {
                onEliminar(serie)
            }
        }
    }

    fun actualizarLista(nuevaLista: List<AnimeSerie>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

    private fun obtenerColorPorGenero(genero: String): Int {
        return when (genero) {
            "Shonen" -> 0xFFF57C00.toInt()
            "Shojo" -> 0xFFD81B60.toInt()
            "Seinen" -> 0xFF5D4037.toInt()
            "Josei" -> 0xFF9C27B0.toInt()
            "Mecha" -> 0xFF455A64.toInt()
            "Isekai" -> 0xFF1E88E5.toInt()
            "SliceOfLife" -> 0xFF81C784.toInt()
            "Horror" -> 0xFFB71C1C.toInt()
            "Fantasia" -> 0xFF7E57C2.toInt()
            "Spokon" -> 0xFFFFA000.toInt()
            else -> 0xFF9E9E9E.toInt()
        }
    }

    private fun obtenerColorPorEstado(estado: String): Int {
        return when (estado) {
            "LaTengoQueVer" -> 0xFF03A9F4.toInt()
            "Viendo" -> 0xFFFFC107.toInt()
            "YaLaVi" -> 0xFF4CAF50.toInt()
            else -> 0xFF9E9E9E.toInt()
        }
    }
}