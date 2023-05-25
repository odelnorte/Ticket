package com.zitro.games.ticket.presentation.registration.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.zitro.games.ticket.presentation.registration.ZGPTMachineListModel
import com.zitro.games.util.common.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ZGPCTicketMachineDropDown(
    modifier: Modifier = Modifier,
    data: List<ZGPTMachineListModel>,
    enabled: MutableState<Boolean>,
    clear: () -> Unit,
    selected: MutableState<ZGPTMachineListModel>
){
    var expanded by remember { mutableStateOf(false) }
    var actionIcon by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            if (enabled.value){
                expanded = !expanded
            }
        }
    ) {
        OutlinedTextField(
            value = selected.value.machineSeries?: "",
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            enabled = enabled.value,
            onValueChange = {

            },
            label = {
                Text(ctx.getString(R.string.zgc_screen_ticket_label_machine))
            },
            trailingIcon = {
                if (!actionIcon){
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                } else {
                    Icon(
                        Icons.Filled.Close,
                        null,
                        Modifier.rotate(if (expanded) 180f else 0f).clickable {
                            clear.invoke()
                            actionIcon = false
                            selected.value = ZGPTMachineListModel()
                        }
                    )
                }
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            val list = if (!selected.value.machineSeries.isNullOrEmpty() ||
                !selected.value.machineModel.isNullOrEmpty()){
                data.filter {
                    ( it.machineSeries
                        ?.lowercase(Locale.ROOT)
                        ?.contains(
                            selected.value.machineSeries?.lowercase(Locale.ROOT)?: "") ?: ""
                            ) as? Boolean ?: false || ( it.machineModel
                        ?.lowercase(Locale.ROOT)
                        ?.contains(
                            selected.value.machineModel?.lowercase(Locale.ROOT)?: "") ?: ""
                            ) as? Boolean ?: false
                }
            } else data

            list.forEach { selectionOption ->
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    text = {
                        Column {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "${selectionOption.machineSeries?: ""} - ${selectionOption.machineModel?: ""}"
                            )
                        }
                    },
                    onClick = {
                        selected.value = selectionOption
                        keyboardController?.hide()
                        actionIcon = true
                        expanded = false
                    }
                )
            }
        }
    }
}