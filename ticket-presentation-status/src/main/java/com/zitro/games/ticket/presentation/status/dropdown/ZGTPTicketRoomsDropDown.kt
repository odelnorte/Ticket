package com.zitro.games.ticket.presentation.status.dropdown

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.zitro.games.ticket.presentation.status.ZGPTRoomsListModel
import com.zitro.games.util.common.R
import java.util.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ZGPCTicketRoomsDropDown(
    modifier: Modifier = Modifier,
    data: List<ZGPTRoomsListModel>,
    status: MutableState<ZGPTRoomsListModel>
){
    var expanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = status.value.roomName,
            modifier = Modifier.menuAnchor(),
            onValueChange = {
                status.value = status.value.copy(roomName = it)
            },
            label = {
                Text(ctx.getString(R.string.zgc_screen_ticket_label_rooms))
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.exposedDropdownSize()
        ) {
            val list = if (status.value.roomName.isNotEmpty()){
                    data.filter {
                        it.roomName
                            .lowercase(Locale.ROOT)
                            .contains(
                                status.value.roomName
                                    .lowercase(Locale.ROOT)
                            )
                    }
                } else data

            list.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Column {
                            Text(text = selectionOption.roomName)
                        }
                    },
                    onClick = {
                        status.value = selectionOption
                        keyboardController?.hide()
                        expanded = false
                    }
                )
            }
        }
    }
}