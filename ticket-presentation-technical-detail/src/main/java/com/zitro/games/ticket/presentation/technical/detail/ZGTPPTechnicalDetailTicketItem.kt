package com.zitro.games.ticket.presentation.technical.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zitro.games.presentation.common.ui.theme.GSVCGray

@Composable
fun ZGTPPTechnicalDetailTicketItem(
    item: ZGPTicketProcessListModel,
    onClick: (ZGPTicketProcessListModel) -> Unit
){

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .border(width = 1.dp.div(2), color = GSVCGray, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(item)
            }
            .padding(10.dp)
        ) {
            Icon(
                Icons.Filled.Menu, "",
                modifier = Modifier
                    .padding(10.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Column {
                Text(
                    text = item.ticket,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = item.room,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = item.description ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ZGTPPTechnicalDetailTicketItem(
    item: ZGPTicketRequestListModel,
    onClick: (ZGPTicketRequestListModel) -> Unit
){

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .border(width = 1.dp.div(2), color = GSVCGray, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(item)
            }
            .padding(10.dp)
        ) {
            Icon(
                Icons.Filled.Menu, "",
                modifier = Modifier
                    .padding(10.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Column {
                Text(
                    text = item.ticket,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = item.room,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = item.description ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}