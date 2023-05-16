package com.zitro.games.ticket.presentation.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketRegistrationInput
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
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
    ) { contentPadding ->

        ConstraintLayout(
            modifier = Modifier.padding(contentPadding)
        ) {
            val (forms, actions) = createRefs()
            val modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()

            Column(
                modifier = Modifier
                    .constrainAs(forms) {
                        top.linkTo(parent.top)
                        bottom.linkTo(actions.top)
                    }
                    .fillMaxHeight(.9f)
                    .fillMaxWidth()
                    .verticalScroll(scroll),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
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
                    value = ticket.region,
                    onValueChange = {
                        ticket = ticket.copy(region = it)
                    }
                )

                OutlinedTextField(
                    modifier = modifier,
                    label = { Text(text = "Sala")},
                    value = ticket.room,
                    onValueChange = {
                        ticket = ticket.copy(room = it)
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

            ConstraintLayout(
                modifier = Modifier
                    .constrainAs(actions) {
                        top.linkTo(forms.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(.1f)
                    .padding(0.dp),
            ) {
                val (btn1, btn2) = createRefs()

                OutlinedButton(
                    modifier = Modifier
                        .constrainAs(btn1) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(btn2.start)
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(start = 30.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(.5f),
                    onClick = { }
                ) {
                    Text(text = "Aceptar")
                }

                OutlinedButton(
                    modifier = Modifier
                        .constrainAs(btn2) {
                            top.linkTo(parent.top)
                            start.linkTo(btn1.end)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(start = 5.dp, top = 10.dp, bottom = 10.dp, end = 30.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(.5f),
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