package com.eden.myandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eden.myandroid.data.repository.CityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CityViewModel(private val repository: CityRepository) : ViewModel() {

    private val _citySuggestions = MutableStateFlow<List<String>>(emptyList())
    val citySuggestions: StateFlow<List<String>> get() = _citySuggestions

    fun fetchCitySuggestions(query: String) {
        viewModelScope.launch {
            _citySuggestions.value = repository.getCitySuggestions(query)
        }
    }
}
