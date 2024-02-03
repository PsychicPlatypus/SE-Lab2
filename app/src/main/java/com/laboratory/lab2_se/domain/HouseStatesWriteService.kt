package com.laboratory.lab2_se.domain

import com.google.firebase.database.FirebaseDatabase

class HouseStatesWriteService {
    private val database = FirebaseDatabase.getInstance()
    private val doorRef = database.getReference("door")
    private val windowRef = database.getReference("window")
    private val lightRef = database.getReference("light")

    fun writeDoorState(state: String) {
        doorRef.setValue(state)
    }

    fun writeWindowState(state: String) {
        windowRef.setValue(state)
    }

    fun writeLightState(state: String) {
        lightRef.setValue(state)
    }
}