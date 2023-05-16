package com.zitro.games.ticket.presentation.pending.view

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ColumnScope.ZGTPPTicketAssign(
    onClick: (String) -> Unit
){
    var ticket by remember { mutableStateOf("") }
}