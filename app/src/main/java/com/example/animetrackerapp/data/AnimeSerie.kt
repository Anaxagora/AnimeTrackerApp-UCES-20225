package com.example.animetrackerapp.data
import java.io.Serializable

enum class Genero {
    Shonen, Shojo, Seinen, Josei, Mecha, Isekai, SliceOfLife, Horror, Fantasia, Spokon
}

enum class Estado {
    LaTengoQueVer, Viendo, YaLaVi
}

data class AnimeSerie(
    val id: Long = System.currentTimeMillis(),
    var nombre: String,
    var anio: Int,
    var comentario: String,
    var genero: Genero,
    var valoracion: Int,
    var estado: Estado
) : Serializable
