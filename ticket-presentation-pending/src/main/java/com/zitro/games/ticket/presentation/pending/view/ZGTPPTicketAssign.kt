package com.zitro.games.ticket.presentation.pending.view

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.*

@Composable
fun ColumnScope.ZGTPPTicketAssign(
    onClick: (String) -> Unit
){
    var ticket by remember { mutableStateOf("") }

    /*Row(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(2f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 15.dp)
                .weight(6f),
            value = ticket,
            onValueChange = {
                ticket = it
            },
            label = { Text(text = "Folio")}
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 15.dp)
                .weight(4f),
            onClick = {
                onClick.invoke(ticket)
            }
        ) {
            Text(
                text = "Asignar"
            )
        }
    }*/
}