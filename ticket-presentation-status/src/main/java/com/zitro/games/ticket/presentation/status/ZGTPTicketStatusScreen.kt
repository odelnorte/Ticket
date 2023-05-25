package com.zitro.games.ticket.presentation.status

import androidx.activity.compose.BackHandler
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
import androidx.navigation.NavHostController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketStatusInput
import com.zitro.games.presentation.common.state.CommonScreenAction
import com.zitro.games.presentation.common.ui.custom.dialog.ZGPCMessageDialog
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCMessageTypeButton
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCParserButton
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCMessageTypeDialog
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCParserDialog
import com.zitro.games.presentation.common.ui.theme.GSVCBase100
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketUpdateStatusRequest
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegionUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRoomsUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketStatusUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketUpdateStatusUseCase
import com.zitro.games.ticket.presentation.status.dropdown.ZGPCTicketRegionDropDown
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
    navController: NavHostController,
){
    val ctx = LocalContext.current

    val statusApiModel = remember { mutableStateOf(ZGPTStatusApiModel()) }
    val roomsApiModel = remember { mutableStateOf(ZGPTRoomsApiModel()) }
    val regionApiModel = remember { mutableStateOf(ZGPTRegionApiModel()) }
    val regionSelected = remember { mutableStateOf(ZGPTRegionListModel()) }
    val statusSelected = remember { mutableStateOf(ZGPTStatusListModel()) }
    val roomSelected = remember { mutableStateOf(ZGPTRoomsListModel()) }
    val openDialogMessage = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.submitAction(ZGTPTicketStatusUiAction.Loading(
            ZGTDTicketStatusRequest(token = catalogInput.dataUser.token ?: "")
        ))
    }

    BackHandler {
        navController.popBackStack()
    }

    ZGPCMessageDialog(
        typeDialog =  ZGPCParserDialog.dialog(
            ctx, ZGPCMessageTypeDialog.ZGPC_DIALOG_SUCCESS
        ),
        buttons = ZGPCParserButton.dialogButton(
            ctx, listOf(
                ZGPCMessageTypeButton.ZGPC_BUTTON_CONTINUE
            )
        ),
        message = ctx.getString(
            R.string.zgc_screen_dialog_ticket_status_message
        ),
        openDialogCustom = openDialogMessage
    ) {
        when(it){
            ZGPCMessageTypeButton.ZGPC_BUTTON_CONTINUE -> {
                navController.popBackStack()
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

                if (statusApiModel.value.listStatus.isNotEmpty()){
                    Text(
                        modifier = Modifier
                            .padding(10.dp),
                        text = ctx.getString(
                            R.string.zgc_screen_ticket_label_status_selection
                        )
                    )

                    ZGPCTicketStatusDropDown(
                        data = statusApiModel.value.listStatus,
                        status = statusSelected
                    )
                }

                if (statusSelected.value.statusId == ZGC_TYPE_STATUS_SERVICE){
                    if (regionApiModel.value.listRegions.isNotEmpty()){
                        Text(
                            modifier = Modifier
                                .padding(10.dp),
                            text = ctx.getString(
                                R.string.zgc_screen_ticket_label_status_regions
                            )
                        )

                        ZGPCTicketRegionDropDown(
                            data = regionApiModel.value.listRegions,
                            status = regionSelected
                        ){
                            roomsApiModel.value = ZGPTRoomsApiModel(listOf())
                            regionSelected.value.regionId?.let {
                                viewModel.submitAction(ZGTPTicketStatusUiAction.LoadingRooms(
                                    ZGTDTicketRoomsRequest(
                                        catalogInput.dataUser.token ?: "",
                                        it
                                    )
                                ))
                            }
                        }
                    } else viewModel.submitAction(ZGTPTicketStatusUiAction.LoadingRegions(
                        ZGTDTicketRegionRequest(
                            catalogInput.dataUser.token ?: ""
                        )
                    ))

                    if (roomsApiModel.value.listRooms.isNotEmpty()){
                        Text(
                            modifier = Modifier
                                .padding(10.dp),
                            text = ctx.getString(
                                R.string.zgc_screen_ticket_label_status_rooms
                            )
                        )

                        ZGPCTicketRoomsDropDown(
                            data = roomsApiModel.value.listRooms,
                            status = roomSelected
                        )
                    }
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
                    enabled = catalogInput.dataUser.usuId != null &&
                            statusSelected.value.statusId != null &&
                            roomSelected.value.roomId != null &&
                            regionSelected.value.regionId != null &&
                            catalogInput.dataUser.token != null,
                    onClick = {
                        viewModel.submitAction(ZGTPTicketStatusUiAction.Status(
                            ZGTDTicketUpdateStatusRequest(
                                usuId = catalogInput.dataUser.usuId ?: 0L,
                                statusId = statusSelected.value.statusId ?: 0,
                                roomId = roomSelected.value.roomId ?: 0,
                                regionId = regionSelected.value.regionId ?: 0,
                                token = catalogInput.dataUser.token ?: ""
                            )
                        ))
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

        viewModel.uiStateFlow.collectAsState().value.let { state ->
            CommonScreenAction(state = state, onAction = { event, request ->
                when(event){
                    ZGPCMessageTypeButton.ZGPC_BUTTON_RETRY -> {
                        when(request){
                            is ZGTDTicketStatusUseCase.Request ->
                                viewModel.submitAction(ZGTPTicketStatusUiAction.Loading(request.statusRequest))
                            is ZGTDTicketRoomsUseCase.Request ->
                                viewModel.submitAction(ZGTPTicketStatusUiAction.LoadingRooms(request.roomRequest))
                            is ZGTDTicketRegionUseCase.Request ->
                                viewModel.submitAction(ZGTPTicketStatusUiAction.LoadingRegions(request.regionRequest))
                            is ZGTDTicketUpdateStatusUseCase.Request ->
                                viewModel.submitAction(ZGTPTicketStatusUiAction.Status(request.statusRequest))
                        }
                    }
                }
            }) {
                when(it){
                    is ZGPTStatusApiModel -> statusApiModel.value = it
                    is ZGPTRoomsApiModel -> roomsApiModel.value = it
                    is ZGPTRegionApiModel -> regionApiModel.value = it
                    is ZGPTSessionUpdateStatusApiModel -> openDialogMessage.value = true
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is ZGTPTicketStatusUiSingleEvent.OpenCamera -> {
                    openDialogMessage.value = false
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}