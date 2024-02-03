package com.laboratory.lab2_se.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.laboratory.lab2_se.domain.HouseStates
import com.laboratory.lab2_se.domain.HouseStatesWriteService

val houseStatesWriteService = HouseStatesWriteService()

@Composable
fun HouseStatesContainer(houseStates: HouseStates) {

    Column {
        Row(
            modifier = Modifier
                .padding(all = 20.dp)
                .height(180.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text(text = "LAMP: ${houseStates.light.uppercase()}")
            }
            Column {
                Text(text = " 💡")
            }
            Column {
                Switch(
                    checked = houseStates.light == "on", onCheckedChange = {
                        houseStatesWriteService.writeLightState(
                            switchToState(it, "on")
                        )
                    }, colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    )
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(all = 20.dp)
                .height(180.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text(text = "DOOR: ${houseStates.door.uppercase()}")
            }
            Column {
                Text(text = "🚪")
            }
            Column {
                Switch(
                    checked = houseStates.door == "open", onCheckedChange = {
                        houseStatesWriteService.writeDoorState(
                            switchToState(it, "open")
                        )
                    }, colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    )
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(all = 20.dp)
                .height(180.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text(text = "WINDOW: ${houseStates.window.uppercase()}")
            }
            Column {
                Text(text = "🪟")
            }
            Column {
                Switch(
                    checked = houseStates.window == "open", onCheckedChange = {
                        houseStatesWriteService.writeWindowState(
                            switchToState(it, "open")
                        )
                    }, colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    )
                )
            }
        }
    }
}

fun switchToState(switch: Boolean, state: String): String {
    return when (state) {
        "open" -> {
            if (switch) "open" else "closed"
        }

        "on" -> {
            if (switch) "on" else "off"
        }

        else -> {
            if (switch) "on" else "off"
        }
    }
}