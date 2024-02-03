package com.laboratory.lab2_se.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HouseStatesViewModel : ViewModel() {
    private val houseStates = HouseStates()
    private val houseStatesMutableLiveData = MutableLiveData<HouseStates>()
    private var statesMutableLiveData = MutableLiveData<States>()
    val houseStatesData: LiveData<HouseStates> = houseStatesMutableLiveData
    var appState: LiveData<States> = statesMutableLiveData


    private val database = FirebaseDatabase.getInstance()
    private val doorRef = database.getReference("door")
    private val windowRef = database.getReference("window")
    private val lightRef = database.getReference("light")


    private fun getDoorListener() = doorRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val doorState = snapshot.value.toString()
            val previousHouseStates = houseStatesMutableLiveData.value
            houseStatesMutableLiveData.value =
                previousHouseStates?.copy(door = doorState) ?: houseStates.copy(door = doorState)
        }

        override fun onCancelled(error: DatabaseError) {
            statesMutableLiveData.value = States.ERROR
        }
    })

    private fun getWindowListener() = windowRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val windowState = snapshot.value.toString()
            val previousHouseStates = houseStatesMutableLiveData.value
            houseStatesMutableLiveData.value = previousHouseStates?.copy(window = windowState)
                ?: houseStates.copy(window = windowState)
        }

        override fun onCancelled(error: DatabaseError) {
            statesMutableLiveData.value = States.ERROR
        }
    })

    private fun getLightListener() = lightRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val lightState = snapshot.value.toString()
            val previousHouseStates = houseStatesMutableLiveData.value
            houseStatesMutableLiveData.value = previousHouseStates?.copy(light = lightState)
                ?: houseStates.copy(light = lightState)
        }

        override fun onCancelled(error: DatabaseError) {
            statesMutableLiveData.value = States.ERROR
        }
    })

    private fun loadHouseStates() {
        statesMutableLiveData.value = States.LOADING
        getDoorListener()
        getWindowListener()
        getLightListener()
        if (statesMutableLiveData.value != States.ERROR) {
            statesMutableLiveData.value = States.SUCCESS
        }
    }

    init {
        loadHouseStates()
    }
}