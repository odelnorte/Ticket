package com.zitro.games.ticket.presentation.technical.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalDetailInput
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ZGTPPTicketTechnicalDetailScreen(
    viewModel: ZGTPTicketTechnicalDetailViewModel,
    technicalInput: CPTicketTechnicalDetailInput,
    navController: NavController
){
    Scaffold(
        topBar = {
            ZGPCTopAppBar(
                "Detalle del Técnico",
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
        ) {
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
                        text = technicalInput.technical.name ?: "",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )

            ZGTPPTicketTechnicalProcessList(viewModel, navController)

            ZGTPPTicketTechnicalRequestList(viewModel, navController)

        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is ZGTPTicketTechnicalDetailUiSingleEvent.Navigate -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}
