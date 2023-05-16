package com.zitro.games.ticket.presentation.pending.view.pending

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketPendingInput
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketPendingViewModel
import com.zitro.games.ticket.presentation.pending.ZGTPTicketPendingUiSingleEvent
import com.zitro.games.ticket.presentation.pending.view.dialog.ZGTPPTicketTechnicalFilterDialog
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZGTPPTicketPendingScreen(
    viewModel: ZGTPPTicketPendingViewModel,
    catalogInput: CPTicketPendingInput,
    navController: NavController
){
    val openFilter = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            ZGPCTopAppBar(
                "Admin",
            )
        }
    ) { contentPadding ->
        ConstraintLayout(
            modifier = Modifier.padding(contentPadding)
        ) {

            val (tab, listTickets) = createRefs()
            var selectedIndex by remember { mutableStateOf(0) }
            val list = listOf("Asignados", "Por Asignar", "Por Sala")


            TabRow(selectedTabIndex = selectedIndex,
                modifier = Modifier
                    .padding(vertical = 0.dp, horizontal = 8.dp)
                    .clip(RoundedCornerShape(50))
                    .padding(1.dp)
                    .constrainAs(tab) {
                        top.linkTo(parent.top)
                        bottom.linkTo(listTickets.top)
                    }.fillMaxHeight(.1f),
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

            val modifier = Modifier
                .constrainAs(listTickets) {
                    top.linkTo(tab.bottom)
                    bottom.linkTo(parent.bottom)
                }.fillMaxHeight(.9f)

            when(selectedIndex){
                0 ->{
                    ZGTPPTicketMyPendingScreen(modifier, viewModel, openFilter, navController)
                }

                1 ->{
                    ZGTPPTicketAllPendingScreen(modifier, viewModel, openFilter, navController)
                }

                2 ->{
                    ZGTPPTicketByRoomPendingScreen(modifier, viewModel, openFilter, navController)
                }
            }
        }
    }

    ZGTPPTicketTechnicalFilterDialog(openFilter)

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
