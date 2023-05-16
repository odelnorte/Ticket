package com.zitro.games.ticket.presentation.technical.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zitro.games.domain.common.entity.ZGCDTypeLoadList
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketDetailInput
import com.zitro.games.presentation.common.navigation.input.ticket.model.CPDataTicketApiModel
import com.zitro.games.presentation.common.ui.custom.list.ZGPCStateComponent
import com.zitro.games.presentation.common.ui.custom.list.ZGWSWidgetList
import com.zitro.games.presentation.common.ui.model.ZGPCListModel

@Composable
fun ZGTPPTicketTechnicalProcessList(
    modifier: Modifier,
    viewModel: ZGTPTicketTechnicalDetailViewModel,
    openFilter: MutableState<Boolean>,
    navController: NavController
){
    val componentState = remember { mutableStateOf(
        ZGPCStateComponent(ZGCDTypeLoadList.REFRESH)
    )}

    val myPending = mutableListOf<ZGPCListModel<ZGPTicketProcessListModel>>()

    myPending.addAll(
        listOf(
            ZGPCListModel(
                id = "MEX-627044-2023",
                name = "CALIENTE CULIACAN",
                number = "Cambio de piezas",
                item = ZGPTicketProcessListModel(
                    ticket = "MEX-627044-2023",
                    room = "CALIENTE CULIACAN",
                    description = "Cambio de piezas"
                )
            ),
            ZGPCListModel(
                id = "MEX-626138-2023",
                name = "CASINO EL DORADO",
                number = "Cero juegado",
                item = ZGPTicketProcessListModel(
                    ticket = "MEX-626138-2023",
                    room = "CASINO EL DORADO",
                    description = "Cero juegado"
                )
            ),
            ZGPCListModel(
                id = "MEX-626126-2023",
                name = "CROWN CITY NANOJOA VS",
                number = "Cero jugado",
                item = ZGPTicketProcessListModel(
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
            .fillMaxHeight()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ZGWSWidgetList(
            modifier = Modifier,
            data = myPending.toSet(),
            title = "Folios en curso",
            textFilter = "",
            reset = remember { mutableStateOf(componentState.value?.reset!!) },
            items = { _, model ->
                ZGTPPTechnicalDetailTicketItem(model){ detail ->
                    viewModel.submitAction(ZGTPTicketTechnicalDetailUiAction.TicketDetail(
                    CPTicketDetailInput(
                            CPDataTicketApiModel(
                                model.ticket,
                                model.room,
                                model.description,
                            )
                        )
                    ))
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