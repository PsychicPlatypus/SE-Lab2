package com.laboratory.lab2_se

import android.speech.SpeechRecognizer
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.laboratory.lab2_se.domain.HouseStatesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.laboratory.lab2_se.domain.States
import com.laboratory.lab2_se.domain.speechRecognitionService
import com.laboratory.lab2_se.ui.components.HouseStatesContainer

@Composable
fun Main(speechRecognizer: SpeechRecognizer?) {
    val houseStatesViewModel: HouseStatesViewModel = viewModel();
    val houseStatesData by houseStatesViewModel.houseStatesData.observeAsState()
    val state by houseStatesViewModel.appState.observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        when (state) {

            States.LOADING -> {
                Text(text = "Loading...")
            }

            States.SUCCESS -> {
                Log.d("Main", "houseData: $houseStatesData")
                if (houseStatesData != null) {
                    HouseStatesContainer(
                        houseStates = houseStatesData!!
                    )
                    Button(onClick = { speechRecognitionService(speechRecognizer) }) {
                        Text("Start speech recognition")
                    }
                }
                else {
                    Box {
                        Text("Loading...")
                    }
                }

            }

            States.ERROR -> {
                Text(
                    text = "An Unexpected Error Occurred",
                    color = Color.Red
                )
            }

            else -> Unit
        }
    }

}
