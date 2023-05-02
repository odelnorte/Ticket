package com.zitro.games.ticket.presentation.adetail.view.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
fun ZGTPPTicketDetailActivity(
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
                text = "Actividades",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    )

    Column(
        Modifier
        .padding(10.dp)
    ) {
        val modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(0.dp)
            .border(1.dp, MaterialTheme.colorScheme.tertiary)

        val modifierText = Modifier
            .padding(2.dp)

        val dataActivity = listOf(
            "Traslado a Sala:" to null,
            "Acceso a Sala:" to null,
            "Atiende Incidencia:" to null,
            "Estatus Incidencia:" to null,
            "Prepara Incidencia:" to null,
        )

        with(LocalContext.current){
            ZGPCRowTwoColumnDefault(
                modifier = Modifier.fillMaxWidth(),
                modifierLeft = modifier,
                modifierRight = modifier,
                modifierText = modifierText,
                list = dataActivity
            )
        }



    }
}