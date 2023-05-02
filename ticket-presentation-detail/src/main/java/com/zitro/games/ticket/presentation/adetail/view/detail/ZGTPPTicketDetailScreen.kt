package com.zitro.games.ticket.presentation.adetail.view.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketDetailInput
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import com.zitro.games.presentation.common.ui.custom.model.bar.ZGCPCTopAppBarActionModel
import com.zitro.games.ticket.presentation.adetail.ZGTPPTicketDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZGTPPTicketDetailScreen(
    viewModel: ZGTPPTicketDetailViewModel,
    catalogInput: CPTicketDetailInput,
    navController: NavController
){
    val dashboardScroll = rememberScrollState()

    Scaffold(
        topBar = {
            ZGPCTopAppBar(
                "Detalle del folio",
                listOf(
                    ZGCPCTopAppBarActionModel(
                        title = "Archivos",
                        icon = null,
                        action = {

                        }
                    ),
                    ZGCPCTopAppBarActionModel(
                        title = "Materiales",
                        icon = null,
                        action = {

                        }
                    ),
                    ZGCPCTopAppBarActionModel(
                        title = "Medios",
                        icon = null,
                        action = {

                        }
                    )
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(dashboardScroll)
                    .fillMaxHeight()
            ) {
                with(LocalContext.current){
                    Column {
                        ZGTPPTicketDetail(
                            viewModel,
                            catalogInput,
                            navController
                        )
                    }

                    Column {
                        ZGTPPTicketDetailActivity(
                            viewModel,
                            catalogInput,
                            navController
                        )
                    }

                    Column {
                        ZGTPPTicketDetailComment(
                            viewModel,
                            catalogInput,
                            navController
                        )
                    }
                }
            }
        }
    )
}