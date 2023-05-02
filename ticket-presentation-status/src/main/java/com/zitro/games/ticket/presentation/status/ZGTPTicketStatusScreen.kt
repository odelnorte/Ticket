package com.zitro.games.ticket.presentation.status

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketStatusInput
import com.zitro.games.presentation.common.state.CommonScreen
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCMessageTypeButton
import com.zitro.games.presentation.common.ui.theme.GSVCBase100
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.presentation.status.dropdown.ZGPCTicketRoomsDropDown
import com.zitro.games.ticket.presentation.status.dropdown.ZGPCTicketStatusDropDown
import com.zitro.games.util.common.R
import com.zitro.games.util.common.ZGC_TYPE_STATUS_SERVICE
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZGTPStatusScreen(
    viewModel: ZGTPTicketStatusViewModel,
    catalogInput: CPTicketStatusInput,
    navController: NavController,
){
    val ctx = LocalContext.current

    val statusApiModel = remember {
        mutableStateOf(ZGPTStatusApiModel())
    }

    val roomsApiModel = remember {
        mutableStateOf(ZGPTRoomsApiModel())
    }

    val statusSelected = remember {
        mutableStateOf(ZGPTStatusListModel())
    }

    val roomSelected = remember {
        mutableStateOf(ZGPTRoomsListModel())
    }

    LaunchedEffect(Unit) {
        viewModel.submitAction(ZGTPTicketStatusUiAction.Loading(
            ZGTDTicketStatusRequest(catalogInput.dataUser.userId ?: 0L)
        ))
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state, onAction = {
            when(it){
                ZGPCMessageTypeButton.ZGPC_BUTTON_RETRY -> {

                }
            }
        }) {
            when(it){
                is ZGPTStatusApiModel -> statusApiModel.value = it
                is ZGPTRoomsApiModel -> roomsApiModel.value = it

            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    Row {
                        Text(
                            modifier = Modifier.weight(1f).fillMaxWidth(),
                            text = "Admin"
                        )

                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(end = 10.dp),
                            textAlign = TextAlign.End,
                            text =  statusSelected.value.statusName
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(contentPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = ctx.getString(
                        R.string.zgc_screen_ticket_label_status_selection
                    )
                )

                if (statusApiModel.value.listStatus.isNotEmpty()){
                    ZGPCTicketStatusDropDown(
                        data = statusApiModel.value.listStatus,
                        status = statusSelected
                    )
                }

                if (statusSelected.value.statusType == ZGC_TYPE_STATUS_SERVICE){
                    if (roomsApiModel.value.listRooms.isNotEmpty()){
                        Text(
                            modifier = Modifier
                                .padding(40.dp),
                            text = ctx.getString(
                                R.string.zgc_screen_ticket_label_status_rooms
                            )
                        )

                        ZGPCTicketRoomsDropDown(
                            data = roomsApiModel.value.listRooms,
                            status = roomSelected
                        )
                    } else viewModel.submitAction(ZGTPTicketStatusUiAction.LoadingRooms(
                        ZGTDTicketRoomsRequest(catalogInput.dataUser.userId ?: 0L)
                    ))
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(start = 35.dp, end = 35.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Text(
                        ctx.getString(
                            R.string.zgc_screen_ticket_btn_status
                        ),
                        color = GSVCBase100,
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is ZGTPTicketStatusUiSingleEvent.OpenCamera -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}