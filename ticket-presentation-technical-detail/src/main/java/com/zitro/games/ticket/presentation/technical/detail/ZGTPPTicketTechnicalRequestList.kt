package com.zitro.games.ticket.presentation.technical.detail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zitro.games.domain.common.entity.ZGCDTypeLoadList
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketDetailInput
import com.zitro.games.presentation.common.navigation.input.ticket.model.CPDataTicketApiModel
import com.zitro.games.presentation.common.ui.custom.list.ZGPCStateComponent
import com.zitro.games.presentation.common.ui.custom.list.ZGWSWidgetList
import com.zitro.games.presentation.common.ui.model.ZGPCListModel

@Composable
fun ColumnScope.ZGTPPTicketTechnicalRequestList(
    viewModel: ZGTPTicketTechnicalDetailViewModel,
    navController: NavController
){
    val componentState = remember { mutableStateOf(
        ZGPCStateComponent(ZGCDTypeLoadList.REFRESH)
    )}

    val myPending = mutableListOf<ZGPCListModel<ZGPTicketRequestListModel>>()
    val ctx = LocalContext.current

    myPending.addAll(
        listOf(
            ZGPCListModel(
                id = "MEX-627044-2023",
                name = "CALIENTE CULIACAN",
                number = "Cambio de piezas",
                item = ZGPTicketRequestListModel(
                    ticket = "MEX-627044-2023",
                    room = "CALIENTE CULIACAN",
                    description = "Cambio de piezas"
                )
            ),
            ZGPCListModel(
                id = "MEX-626138-2023",
                name = "CASINO EL DORADO",
                number = "Cero juegado",
                item = ZGPTicketRequestListModel(
                    ticket = "MEX-626138-2023",
                    room = "CASINO EL DORADO",
                    description = "Cero juegado"
                )
            ),
            ZGPCListModel(
                id = "MEX-626126-2023",
                name = "CROWN CITY NANOJOA VS",
                number = "Cero jugado",
                item = ZGPTicketRequestListModel(
                    ticket = "MEX-626126-2023",
                    room = "CROWN CITY NANOJOA VS",
                    description = "Cero jugado"
                )
            )
        )
    )

    Row(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(1f)
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ZGWSWidgetList(
            modifier = Modifier,
            data = myPending.toSet(),
            title = "Solicitudes de folios",
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
            onPage = {
                if (componentState.value?.typeLoad == ZGCDTypeLoadList.REFRESH){

                }
            }
        )
    }
}