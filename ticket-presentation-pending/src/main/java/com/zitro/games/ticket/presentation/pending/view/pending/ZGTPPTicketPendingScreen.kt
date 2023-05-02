package com.zitro.games.ticket.presentation.pending.view.pending

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketPendingInput
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketPendingViewModel
import com.zitro.games.ticket.presentation.pending.ZGTPTicketPendingUiSingleEvent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZGTPPTicketPendingScreen(
    viewModel: ZGTPPTicketPendingViewModel,
    catalogInput: CPTicketPendingInput,
    navController: NavController
){
    Scaffold(
        topBar = {
            ZGPCTopAppBar(
                "Admin",
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
        ) {
            var selectedIndex by remember { mutableStateOf(0) }
            val list = listOf("Asignados", "Por Asignar", "Por Sala")


            TabRow(selectedTabIndex = selectedIndex,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .clip(RoundedCornerShape(50))
                    .padding(1.dp),
                indicator = {
                    Box {

                    }
                }
            ) {
                list.forEachIndexed { index, text ->
                    val selected = selectedIndex == index
                    Tab(
                        modifier = if (selected) Modifier
                            .clip(RoundedCornerShape(50))
                            .background(
                                MaterialTheme.colorScheme.primary
                            )
                        else Modifier
                            .clip(RoundedCornerShape(50))
                            .background(
                                MaterialTheme.colorScheme.onPrimary
                            ),
                        selected = selected,
                        onClick = { selectedIndex = index },
                        text = { Text(
                            text = text,
                            color = if (selected)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.tertiary
                        ) }
                    )
                }
            }

            when(selectedIndex){
                0 ->{
                    ZGTPPTicketMyPendingScreen(viewModel, navController)
                }

                1 ->{
                    ZGTPPTicketAllPendingScreen(viewModel, navController)
                }

                2 ->{
                    ZGTPPTicketByRoomPendingScreen(viewModel, navController)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is ZGTPTicketPendingUiSingleEvent.Detail -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}
