package com.herbarium.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herbarium.data.model.Plant
import com.herbarium.data.repository.PlantRepository
import kotlinx.coroutines.launch

class PlantViewModel(private val repository: PlantRepository): ViewModel() {

    // Those are the plants converted from the Room database
    // It is a LiveData object so that it automatically updates the UI
    val plants: LiveData<List<Plant>> = repository.getPlants()

    private val _selectedPlant = MutableLiveData<Plant?>()
    val selectedPlant: LiveData<Plant?> get() = _selectedPlant

    private val _statusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String> get() = _statusMessage

    fun fetchPlantById(id: String) {
        viewModelScope.launch {
            try {
                val plant = repository.getPlantById(id)
                _selectedPlant.postValue(plant)
            } catch (e: Exception) {
                _statusMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun addPlant(newPlant: Plant) {
        viewModelScope.launch {
            try {
                repository.insert(newPlant)
                _statusMessage.value = "Plant added successfully"
            } catch (e: Exception) {
                _statusMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun syncWithSupabase() {
        viewModelScope.launch {
            try {
                repository.synchronize()
                _statusMessage.value = "Synced with Supabase successfully"
            } catch (e: Exception) {
                _statusMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun deletePlant(deletedPlant: Plant) {
        viewModelScope.launch {
            try {
                repository.markForDeletion(deletedPlant)
                _statusMessage.value = "Plant deleted successfully"
            } catch (e: Exception) {
                _statusMessage.value = "Error: ${e.message}"
            }
        }
    }
}