package com.zitro.games.ticket.presentation.pending.view.pending

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.navigation.NavController
import com.zitro.games.domain.common.entity.ZGCDTypeLoadList
import com.zitro.games.presentation.common.state.CommonScreen
import com.zitro.games.presentation.common.ui.custom.list.ZGPCStateComponent
import com.zitro.games.presentation.common.ui.custom.list.ZGWSWidgetList
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketPending
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketPendingViewModel
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketRoomsApiModel
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketRoomsModel
import com.zitro.games.ticket.presentation.pending.ZGTPTicketPendingUiAction
import java.util.Locale

@Composable
fun ConstraintLayoutScope.ZGTPPTicketByRoomPendingScreen(
    modifier: Modifier,
    viewModel: ZGTPPTicketPendingViewModel,
    openFilter: MutableState<Boolean>,
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
        /*viewModel.submitAction(
            ZGTPTicketPendingUiAction.LoadingRooms(
                ZGTDTicketRoomsRequest(0L)
            ))*/
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
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

    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
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
                                ?.lowercase(Locale.ROOT) ?: ""
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
            openFilter = openFilter,
            onPage = {
                if (componentState.value?.typeLoad == ZGCDTypeLoadList.REFRESH){

                }
            }
        )
    }
}