package com.zitro.games.ticket.presentation.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
            ZGTDTicketStatusRequest(token = catalogInput.dataUser.token ?: "")
        ))
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state, onAction = {
            when(it){
                ZGPCMessageTypeButton.ZGPC_BUTTON_RETRY -> {
                    viewModel.submitAction(ZGTPTicketStatusUiAction.Loading(
                        ZGTDTicketStatusRequest(token = catalogInput.dataUser.token ?: "")
                    ))
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
                    Column {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Admin"
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        ConstraintLayout(
            modifier = Modifier.padding(contentPadding)
        ) {
            val (column1, column2) = createRefs()
            Column(
                modifier = Modifier
                    .constrainAs(column1) {
                        top.linkTo(parent.top, margin = 0.dp)
                        bottom.linkTo(column2.top, margin = 0.dp)
                    }
                    .fillMaxHeight(.9f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
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

                if (statusSelected.value.statusId == ZGC_TYPE_STATUS_SERVICE){
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
                    .constrainAs(column2) {
                        top.linkTo(column1.bottom, margin = 0.dp)
                        bottom.linkTo(parent.bottom, margin = 20.dp)
                    }
                    .fillMaxHeight(.1f)
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
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