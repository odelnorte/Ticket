package com.zitro.games.ticket.presentation.adetail.view.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketDetailInput
import com.zitro.games.presentation.common.ui.custom.row.ZGPCRowTwoColumnDefault
import com.zitro.games.ticket.presentation.adetail.ZGTPPTicketDetailViewModel

@Composable
fun ZGTPPTicketDetail(
    viewModel: ZGTPPTicketDetailViewModel,
    catalogInput: CPTicketDetailInput,
    navController: NavController
){
    Tab(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(50))
            .background(
                MaterialTheme.colorScheme.primary
            ),
        selected = true,
        onClick = {

        },
        text = {
            Text(
                text = "Renta",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    )

    Column(
        Modifier
        .padding(10.dp)
    ) {
        val modifier = Modifier
            .height(25.dp)

        val modifierText = Modifier
            .padding(2.dp)

        val dataDescription = listOf(
            "Folio:" to catalogInput.dataTicket.ticket,
            "Sala:" to catalogInput.dataTicket.room,
            "Falla:" to "Cambio de Piezas",
            "Subfalla:" to "Eliminador da",
            "Licencia:" to "06/027777",
            "Juego:" to "POWER FOUR"
        )

        with(LocalContext.current){
            ZGPCRowTwoColumnDefault(
                modifier = Modifier.fillMaxWidth(),
                modifierLeft = modifier,
                modifierRight = modifier,
                modifierText = modifierText,
                list = dataDescription
            )
        }


        val dataActivity = listOf(
            "Folio:" to catalogInput.dataTicket.ticket,
            "Sala:" to catalogInput.dataTicket.room,
            "Falla:" to "Cambio de Piezas",
            "Subfalla:" to "Eliminador da",
            "Licencia:" to "06/027777",
            "Juego:" to "POWER FOUR"
        )

    }
}