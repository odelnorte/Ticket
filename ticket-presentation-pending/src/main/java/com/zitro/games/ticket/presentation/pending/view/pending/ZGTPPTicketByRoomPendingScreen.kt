package com.zitro.games.ticket.presentation.pending.view.pending

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zitro.games.domain.common.entity.ZGCDTypeLoadList
import com.zitro.games.presentation.common.state.CommonScreen
import com.zitro.games.presentation.common.ui.custom.list.ZGPCStateComponent
import com.zitro.games.presentation.common.ui.custom.list.ZGWSWidgetList
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCMessageTypeButton
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.presentation.pending.*
import com.zitro.games.ticket.presentation.pending.view.ZGTPPTicketAssign
import com.zitro.games.ticket.presentation.pending.view.dropdown.ZGTPPTicketRoomsDropDown
import com.zitro.games.util.common.R
import java.util.*

@Composable
fun ColumnScope.ZGTPPTicketByRoomPendingScreen(
    viewModel: ZGTPPTicketPendingViewModel,
    navController: NavController
){
    val componentState = remember { mutableStateOf(
        ZGPCStateComponent(ZGCDTypeLoadList.REFRESH)
    )}

    val myPending = mutableListOf<ZGPCListModel<ZGTPPTicketPending>>()
    val ctx = LocalContext.current

    val roomsApiModel = remember {
        mutableStateOf(ZGTPPTicketRoomsApiModel())
    }

    val roomSelected = remember {
        mutableStateOf(ZGTPPTicketRoomsModel())
    }

    LaunchedEffect(Unit) {
        viewModel.submitAction(
            ZGTPTicketPendingUiAction.LoadingRooms(
                ZGTDTicketRoomsRequest(0L)
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
                is ZGTPPTicketRoomsApiModel -> roomsApiModel.value = it
            }
        }
    }

    myPending.addAll(
        listOf(
            ZGPCListModel(
                id = "MEX-627044-2023",
                name = "CALIENTE CULIACAN",
                number = "Cambio de piezas",
                item = ZGTPPTicketPending(
                    ticket = "MEX-627044-2023",
                    room = "CALIENTE CULIACAN",
                    description = "Cambio de piezas"
                )
            ),
            ZGPCListModel(
                id = "MEX-626138-2023",
                name = "CASINO EL DORADO",
                number = "Cero juegado",
                item = ZGTPPTicketPending(
                    ticket = "MEX-626138-2023",
                    room = "CASINO EL DORADO",
                    description = "Cero juegado"
                )
            ),
            ZGPCListModel(
                id = "MEX-626126-2023",
                name = "CROWN CITY NANOJOA VS",
                number = "Cero jugado",
                item = ZGTPPTicketPending(
                    ticket = "MEX-626126-2023",
                    room = "CROWN CITY NANOJOA VS",
                    description = "Cero jugado"
                )
            )
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(2f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp),
                text = ctx.getString(
                    R.string.zgc_screen_ticket_label_status_rooms
                )
            )

            ZGTPPTicketRoomsDropDown(
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp, top = 20.dp),
                data = roomsApiModel.value.listRooms,
                room = roomSelected
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(9f)
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ZGWSWidgetList(
                modifier = Modifier,
                data = (if (myPending.isNotEmpty()){
                    myPending.filter {
                        it.item.room
                            .lowercase(Locale.ROOT)
                            .contains(
                                roomSelected.value.roomName
                                    .lowercase(Locale.ROOT)
                            )
                    }
                } else myPending).toSet(),
                title = "Folios por Sala",
                textFilter = "",
                reset = remember { mutableStateOf(componentState.value?.reset!!) },
                items = { _, model ->
                    ZGTPPTicketPendingItem(model){ detail ->
                        viewModel.submitAction(ZGTPTicketPendingUiAction.Detail(detail))
                    }
                },
                onPage = {
                    if (componentState.value?.typeLoad == ZGCDTypeLoadList.REFRESH){

                    }
                }
            )
        }

        ZGTPPTicketAssign {

        }
    }
}