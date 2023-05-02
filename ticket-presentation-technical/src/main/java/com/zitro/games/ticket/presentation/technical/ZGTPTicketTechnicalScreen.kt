package com.zitro.games.ticket.presentation.technical

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zitro.games.domain.common.entity.ZGCDTypeLoadList
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalDetailInput
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalInput
import com.zitro.games.presentation.common.navigation.input.ticket.model.CPDataTechnicalApiModel
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import com.zitro.games.presentation.common.ui.custom.list.ZGPCStateComponent
import com.zitro.games.presentation.common.ui.custom.list.ZGWSWidgetList
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.util.common.ZGUTypeStatus
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ZGTPPTicketTechnicalScreen(
    viewModel: ZGTPTicketTechnicalViewModel,
    technicalInput: CPTicketTechnicalInput,
    navController: NavController
){
    val componentState = remember { mutableStateOf(
        ZGPCStateComponent(ZGCDTypeLoadList.REFRESH)
    )}

    Scaffold(
        topBar = {
            ZGPCTopAppBar(
                "Asignación de folio",
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxHeight()
            ) {
                val technicals = mutableListOf<ZGPCListModel<ZGPTicketTechnicalListModel>>()

                technicals.addAll(
                    listOf(
                        ZGPCListModel(
                            id = "0",
                            name = "Brandon Alexis Martinez",
                            number = "515151515",
                            item = ZGPTicketTechnicalListModel(
                                technicalId = 0,
                                technicalName = "Brandon Alexis Martinez",
                                status = ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE
                            )
                        ),
                        ZGPCListModel(
                            id = "0",
                            name = "Enrique Roldan",
                            number = "63566356",
                            item = ZGPTicketTechnicalListModel(
                                technicalId = 0,
                                technicalName = "Enrique Roldan",
                                status = ZGUTypeStatus.ZGC_TYPE_STATUS_DAY_OF_REST
                            )
                        ),
                        ZGPCListModel(
                            id = "0",
                            name = "Fernando de Jesus Cervantes",
                            number = "6543567",
                            item = ZGPTicketTechnicalListModel(
                                technicalId = 0,
                                technicalName = "Fernando de Jesus Cervantes",
                                status = ZGUTypeStatus.ZGC_TYPE_STATUS_MIX
                            )
                        ),
                        ZGPCListModel(
                            id = "0",
                            name = "Carlos Arturo Cortes",
                            number = "2634534",
                            item = ZGPTicketTechnicalListModel(
                                technicalId = 0,
                                technicalName = "Carlos Arturo Cortes",
                                status = ZGUTypeStatus.ZGC_TYPE_STATUS_INABILITY
                            )
                        ),
                        ZGPCListModel(
                            id = "0",
                            name = "Israel Cuellar",
                            number = "75685687",
                            item = ZGPTicketTechnicalListModel(
                                technicalId = 0,
                                technicalName = "Israel Cuellar",
                                status = ZGUTypeStatus.ZGC_TYPE_STATUS_VACATION
                            )
                        )
                    )
                )

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
                        data = technicals.toSet(),
                        title = "Técnicos disponibles",
                        textFilter = "",
                        reset = remember { mutableStateOf(componentState.value?.reset!!) },
                        items = { _, model ->
                            ZGTPPTicketTechnicalItem(model){ detail ->
                                viewModel.submitAction(ZGTPTicketTechnicalUiAction.TechnicalDetail(
                                    CPTicketTechnicalDetailInput(
                                        technicalInput.dataUser,
                                        CPDataTechnicalApiModel(
                                            model.technicalId.toLong(),
                                            model.technicalName
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
        }
    )

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is ZGTPTicketTechnicalUiSingleEvent.Navigate -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}
