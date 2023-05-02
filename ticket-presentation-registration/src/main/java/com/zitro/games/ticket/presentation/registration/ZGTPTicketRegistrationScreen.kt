package com.zitro.games.ticket.presentation.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketRegistrationInput
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ZGTPTicketRegistrationScreen (
    viewModel: ZGTPTicketRegistrationViewModel,
    registrationInput: CPTicketRegistrationInput,
    navController: NavController
){
    var ticket by remember {
        mutableStateOf(ZGPTRegistrationApiModel())
    }
    val scroll = rememberScrollState()

    Scaffold(
        topBar = {
            ZGPCTopAppBar(
                "Solicitud de folios"
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            val modifier = Modifier.padding(15.dp).fillMaxWidth()

            Column(
                modifier.fillMaxHeight()
                    .weight(9f)
                    .verticalScroll(scroll)
            ) {

                OutlinedTextField(
                    modifier = modifier,
                    label = { Text(text = "Cliente")},
                    value = ticket.client,
                    onValueChange = {
                        ticket = ticket.copy(client = it)
                    }
                )

                OutlinedTextField(
                    modifier = modifier,
                    label = { Text(text = "Region")},
                    value = ticket.client,
                    onValueChange = {
                        ticket = ticket.copy(client = it)
                    }
                )

                OutlinedTextField(
                    modifier = modifier,
                    label = { Text(text = "Sala")},
                    value = ticket.client,
                    onValueChange = {
                        ticket = ticket.copy(client = it)
                    }
                )

                OutlinedTextField(
                    modifier = modifier,
                    label = { Text(text = "Modelo")},
                    value = ticket.model,
                    onValueChange = {
                        ticket = ticket.copy(model = it)
                    }
                )

                OutlinedTextField(
                    modifier = modifier,
                    label = { Text(text = "No. Serie")},
                    value = ticket.noSeries,
                    onValueChange = {
                        ticket = ticket.copy(noSeries = it)
                    }
                )

                OutlinedTextField(
                    modifier = modifier,
                    label = { Text(text = "Falla")},
                    value = ticket.failure,
                    onValueChange = {
                        ticket = ticket.copy(failure = it)
                    }
                )

                OutlinedTextField(
                    modifier = modifier,
                    label = { Text(text = "Info. Adicional")},
                    value = ticket.additionalInfo,
                    onValueChange = {
                        ticket = ticket.copy(additionalInfo = it)
                    }
                )
            }

            Row(
                modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(1f),
                    onClick = { }
                ) {
                    Text(text = "Aceptar")
                }

                OutlinedButton(
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(1f),
                    onClick = {
                        viewModel.submitAction(ZGTPTicketRegistrationUiAction.Technical(
                            registrationInput.dataUser.userId ?: 0L
                        ))
                    }
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Enviar a otro técnico"
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is ZGTPTicketRegistrationUiSingleEvent.Navigation -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}