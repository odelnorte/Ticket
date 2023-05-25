package com.zitro.games.ticket.presentation.technical

import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zitro.games.ticket.presentation.technical.model.ZGPTicketTechnicalListModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZGTPPTicketTechnicalItem(
    item: ZGPTicketTechnicalListModel,
    status: Boolean = false,
    onClick: (ZGPTicketTechnicalListModel) -> Unit
){
    val modifier  = if (!status) Modifier.background(
            item.technicalStatus.color
        )
    else Modifier

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth(),
    ) {
        Row(modifier = modifier
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
                if (!status) {
                    Text(
                        text = item.technicalUser.userName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )

                    Text(
                        text = item.technicalStatus.statusName,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                } else {
                    Text(
                        text = item.technicalUser.userName,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = item.technicalStatus.statusName,
                        style = MaterialTheme.typography.bodySmall
                    )

                }

            }
        }
    }
}