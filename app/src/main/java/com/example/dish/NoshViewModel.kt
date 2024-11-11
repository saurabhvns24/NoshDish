package com.example.dish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoshViewModel : ViewModel() {
    private val repository = NoshRepository()
    private val _dishes = MutableStateFlow<List<Dish>>(emptyList())
    val dishes: StateFlow<List<Dish>> = _dishes

    init {
        viewModelScope.launch {
            try {
                val dishes = repository.getDishes()
                _dishes.value = dishes
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }
}