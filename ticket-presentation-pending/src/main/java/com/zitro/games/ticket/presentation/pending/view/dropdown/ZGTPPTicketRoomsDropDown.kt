package com.zitro.games.ticket.presentation.pending.view.dropdown

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketRoomsModel
import com.zitro.games.util.common.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ZGTPPTicketRoomsDropDown(
    modifier: Modifier = Modifier,
    data: List<ZGTPPTicketRoomsModel>,
    room: MutableState<ZGTPPTicketRoomsModel>
){
    var expanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = room.value.roomName,
            modifier = Modifier.menuAnchor(),
            onValueChange = {
                room.value = room.value.copy(roomName = it)
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
            //modifier = Modifier.exposedDropdownSize()
        ) {
            val list = if (room.value.roomName.isNotEmpty()){
                data.filter {
                    it.roomName
                        .lowercase(Locale.ROOT)
                        .contains(
                            room.value.roomName
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
                        room.value = selectionOption
                        keyboardController?.hide()
                        expanded = false
                    }
                )
            }
        }
    }
}