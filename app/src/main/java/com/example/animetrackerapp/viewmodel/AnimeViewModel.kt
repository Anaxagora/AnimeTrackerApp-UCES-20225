package com.example.animetrackerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animetrackerapp.data.AnimeSerie

class AnimeViewModel : ViewModel() {
    private val _series = MutableLiveData<MutableList<AnimeSerie>>(mutableListOf())
    val series: LiveData<MutableList<AnimeSerie>> get() = _series

    fun agregarSerie(serie: AnimeSerie) {
        _series.value?.add(serie)
        _series.value = _series.value // trigger update
    }

    fun eliminarSerie(id: Long) {
        _series.value = _series.value?.filter { it.id != id }?.toMutableList()
    }

    fun editarSerie(serie: AnimeSerie) {
        val index = _series.value?.indexOfFirst { it.id == serie.id }
        index?.let {
            if (it != -1) {
                _series.value?.set(it, serie)
                _series.value = _series.value
            }
        }
    }
}
