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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zitro.games.domain.common.entity.ZGCDTypeLoadList
import com.zitro.games.presentation.common.navigation.NavRoutes
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalInput
import com.zitro.games.presentation.common.navigation.navigateDest
import com.zitro.games.presentation.common.state.CommonScreenAction
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import com.zitro.games.presentation.common.ui.custom.dialog.ZGPCFilterDialog
import com.zitro.games.presentation.common.ui.custom.dialog.ZGPCMessageDialog
import com.zitro.games.presentation.common.ui.custom.list.ZGPCStateComponent
import com.zitro.games.presentation.common.ui.custom.list.ZGWSWidgetList
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCMessageTypeButton
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCParserButton
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCFilterTypeDialog
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCMessageTypeDialog
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCParserDialog
import com.zitro.games.presentation.common.ui.custom.model.dropdown.ZGPCFilterTypeDropDown
import com.zitro.games.presentation.common.ui.custom.model.dropdown.ZGPCModelListDropDown
import com.zitro.games.presentation.common.ui.custom.model.dropdown.ZGPCParserDropDown
import com.zitro.games.presentation.common.ui.custom.model.textfield.ZGPCFilterTypeTextField
import com.zitro.games.presentation.common.ui.custom.model.textfield.ZGPCParserTextField
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import com.zitro.games.ticket.domain.usecase.ZGTDTicketReassignUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketTechnicalUseCase
import com.zitro.games.ticket.presentation.technical.model.ZGPTicketReassignApiModel
import com.zitro.games.ticket.presentation.technical.model.ZGPTicketTechnicalListModel
import com.zitro.games.ticket.presentation.technical.model.ZGPTicketTechnicalTechnicalApiModel
import com.zitro.games.util.common.R
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZGTPPTicketTechnicalScreen(
    viewModel: ZGTPTicketTechnicalViewModel,
    technicalInput: CPTicketTechnicalInput,
    navController: NavHostController
){

    val openFilter = remember { mutableStateOf(false) }
    val ctx = LocalContext.current
    val componentState = remember { mutableStateOf(
        ZGPCStateComponent(ZGCDTypeLoadList.REFRESH)
    )}
    val massage = remember { mutableStateOf("")}
    val openDialogMessage = remember { mutableStateOf(false) }
    val openDialogQuestion = remember { mutableStateOf(false) }
    var technicalId by remember { mutableStateOf(0) }

    val technicals = remember {
        mutableStateOf<List<ZGPCListModel<ZGPTicketTechnicalListModel>>>(
            listOf()
        )
    }

    LaunchedEffect(Unit) {
        viewModel.submitAction(ZGTPTicketTechnicalUiAction.Loading(
            ZGTDTicketTechnicalRequest(
                token = technicalInput.dataUser.token ?: "",
                regionId = technicalInput.dataRegion.regionId ?: 0
            )
        ))
    }

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
                        data = technicals.value.toSet(),
                        title = "Técnicos disponibles",
                        textFilter = "",
                        reset = remember { mutableStateOf(componentState.value?.reset!!) },
                        items = { _, model ->
                            ZGTPPTicketTechnicalItem(model) {
                                massage.value = ctx.getString(
                                    R.string.zgc_screen_dialog_ticket_reassign_question, it.technicalUser.userName
                                )
                                openDialogQuestion.value = true
                                technicalId = it.technicalId
                            }
                        },
                        openFilter = openFilter,
                        onPage = {
                            if (componentState.value?.typeLoad == ZGCDTypeLoadList.REFRESH){

                            }
                        }
                    )
                }



                viewModel.uiStateFlow.collectAsState().value.let { state ->
                    CommonScreenAction(state = state, onAction = { event, request ->
                        when(event){
                            ZGPCMessageTypeButton.ZGPC_BUTTON_RETRY -> {
                                when(request){
                                    is ZGTDTicketTechnicalUseCase.Request ->
                                        viewModel.submitAction(ZGTPTicketTechnicalUiAction.Loading(request.technicalRequest))
                                    is ZGTDTicketReassignUseCase.Request ->
                                        viewModel.submitAction(ZGTPTicketTechnicalUiAction.TechnicalTicketReassign(request.reassignRequest))
                                }
                            }
                        }
                    })  {
                        when(it){
                            is ZGPTicketTechnicalTechnicalApiModel -> technicals.value = it.listStatus
                            is ZGPTicketReassignApiModel -> {
                                openDialogMessage.value = true
                                massage.value = ctx.getString(
                                    R.string.zgc_screen_dialog_ticket_reassign_message, it.ticketId.toString()
                                )
                            }
                        }
                    }
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

    ZGPCMessageDialog(
        typeDialog =  ZGPCParserDialog.dialog(
            ctx, ZGPCMessageTypeDialog.ZGPC_DIALOG_WARNING
        ),
        buttons = ZGPCParserButton.dialogButton(
            ctx, listOf(
                ZGPCMessageTypeButton.ZGPC_BUTTON_CANCEL,
                ZGPCMessageTypeButton.ZGPC_BUTTON_CONTINUE,
            )
        ),
        message = massage.value,
        openDialogCustom = openDialogQuestion
    ) {
        when(it){
            ZGPCMessageTypeButton.ZGPC_BUTTON_CANCEL -> {

            }

            ZGPCMessageTypeButton.ZGPC_BUTTON_CONTINUE -> {

            }
        }
    }

    ZGPCMessageDialog(
        typeDialog =  ZGPCParserDialog.dialog(
            ctx, ZGPCMessageTypeDialog.ZGPC_DIALOG_WARNING
        ),
        buttons = ZGPCParserButton.dialogButton(
            ctx, listOf(
                ZGPCMessageTypeButton.ZGPC_BUTTON_CONTINUE,
            )
        ),
        message = massage.value,
        openDialogCustom = openDialogMessage
    ) {
        when(it){
            ZGPCMessageTypeButton.ZGPC_BUTTON_CONTINUE -> {
                navController.navigateDest(NavRoutes.AtServiceHome.route)
            }
        }
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
