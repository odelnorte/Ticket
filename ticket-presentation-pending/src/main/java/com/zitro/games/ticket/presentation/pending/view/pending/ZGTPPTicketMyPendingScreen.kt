package com.zitro.games.ticket.presentation.pending.view.pending

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.navigation.NavController
import com.zitro.games.domain.common.entity.ZGCDTypeLoadList
import com.zitro.games.presentation.common.ui.custom.list.ZGPCStateComponent
import com.zitro.games.presentation.common.ui.custom.list.ZGWSWidgetList
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketPending
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketPendingViewModel
import com.zitro.games.ticket.presentation.pending.ZGTPTicketPendingUiAction

@Composable
fun ConstraintLayoutScope.ZGTPPTicketMyPendingScreen(
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
            data = myPending.toSet(),
            title = "Mis folios pendientes",
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