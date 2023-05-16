package com.zitro.games.ticket.presentation.technical

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zitro.games.domain.common.entity.ZGCDTypeLoadList
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalDetailInput
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalInput
import com.zitro.games.presentation.common.navigation.input.ticket.model.CPDataTechnicalApiModel
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import com.zitro.games.presentation.common.ui.custom.dialog.ZGPCFilterDialog
import com.zitro.games.presentation.common.ui.custom.list.ZGPCStateComponent
import com.zitro.games.presentation.common.ui.custom.list.ZGWSWidgetList
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCMessageTypeButton
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCParserButton
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCFilterTypeDialog
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCParserDialog
import com.zitro.games.presentation.common.ui.custom.model.dropdown.ZGPCFilterTypeDropDown
import com.zitro.games.presentation.common.ui.custom.model.dropdown.ZGPCModelListDropDown
import com.zitro.games.presentation.common.ui.custom.model.dropdown.ZGPCParserDropDown
import com.zitro.games.presentation.common.ui.custom.model.textfield.ZGPCFilterTypeTextField
import com.zitro.games.presentation.common.ui.custom.model.textfield.ZGPCParserTextField
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.util.common.ZGUTypeStatus
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZGTPPTicketTechnicalScreen(
    viewModel: ZGTPTicketTechnicalViewModel,
    technicalInput: CPTicketTechnicalInput,
    navController: NavController
){
    val componentState = remember { mutableStateOf(
        ZGPCStateComponent(ZGCDTypeLoadList.REFRESH)
    )}

    val openFilter = remember { mutableStateOf(false) }
    val ctx = LocalContext.current

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
                        openFilter = openFilter,
                        onPage = {
                            if (componentState.value?.typeLoad == ZGCDTypeLoadList.REFRESH){

                            }
                        }
                    )
                }
            }
        }
    )


    ZGPCFilterDialog(
        typeDialog = ZGPCParserDialog.dialog(
            ctx, ZGPCFilterTypeDialog.ZGPC_DIALOG_FILTER_LIST_PENDING_TECHNICAL
        ),
        btn =  ZGPCParserButton.dialogButton(
            ctx, listOf(
                ZGPCMessageTypeButton.ZGPC_BUTTON_CANCEL,
                ZGPCMessageTypeButton.ZGPC_BUTTON_ACCEPT
            )
        ),
        dropDowns = ZGPCParserDropDown.dialogDropDown(
            ctx, listOf(
                ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_ROOM to listOf(
                    ZGPCModelListDropDown(
                        id = 1,
                        content = "CALIENTE CULIACAN",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_ROOM
                    ),
                    ZGPCModelListDropDown(
                        id = 2,
                        content = "CASINO EL DORADO",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_ROOM
                    ),
                    ZGPCModelListDropDown(
                        id = 3,
                        content = "CROWN CITY NANOJOA VS",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_ROOM
                    )
                ),
                ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_REGION to listOf(
                    ZGPCModelListDropDown(
                        id = 30,
                        content = "Alava",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_REGION
                    ),
                    ZGPCModelListDropDown(
                        id = 18,
                        content = "Granada",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_REGION
                    ),
                    ZGPCModelListDropDown(
                        id = 38,
                        content = "La Coruña",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_REGION
                    ),
                    ZGPCModelListDropDown(
                        id = 47,
                        content = "Zamora",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_REGION
                    )
                ),
                ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_CLIENT to listOf(
                    ZGPCModelListDropDown(
                        id = 18,
                        content = "Gran Madrid Torrelodones",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_CLIENT
                    ),
                    ZGPCModelListDropDown(
                        id = 38,
                        content = "Admiral Casino San Roque ",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_CLIENT
                    ),
                    ZGPCModelListDropDown(
                        id = 47,
                        content = "Casino Barcelona",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_CLIENT
                    )
                )
            )
        ),
        textFields = ZGPCParserTextField.dialogTextField(
            ctx, listOf(
                ZGPCFilterTypeTextField.ZGPC_TEXT_FIELD_TICKET
            )
        ),
        openDialogCustom = openFilter
    ) {

    }

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
