package com.zitro.games.ticket.presentation.status.dropdown

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W100
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zitro.games.ticket.presentation.status.ZGPTStatusListModel
import com.zitro.games.util.common.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZGPCTicketStatusDropDown(
    modifier: Modifier = Modifier,
    data: List<ZGPTStatusListModel>,
    status: MutableState<ZGPTStatusListModel>
){
    var expanded by remember { mutableStateOf(false) }
    val ctx = LocalContext.current

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = status.value.statusName,
            modifier = Modifier.menuAnchor(),
            onValueChange = { },
            leadingIcon = {
                ( try {
                    status.value.getIcon()
                } catch (ex: Exception){
                    ex.printStackTrace()
                } as? Int)?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "",
                        Modifier.size(24.dp)
                    )
                }
            },
            label = {
                Text(ctx.getString(R.string.zgc_screen_ticket_label_status))
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
            data.forEach { selectionOption ->
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = selectionOption.getIcon()),
                            contentDescription = ""
                        )
                    },
                    text = {
                        Column {
                            Text(text = selectionOption.statusName)
                            Text(text = selectionOption.statusDescription, fontWeight = W100, fontSize = 10.sp)
                        }
                    },
                    onClick = {
                        status.value = selectionOption
                        expanded = false
                    }
                )
            }
        }
    }
}