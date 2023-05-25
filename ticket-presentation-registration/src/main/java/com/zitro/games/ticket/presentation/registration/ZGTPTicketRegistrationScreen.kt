package com.zitro.games.ticket.presentation.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketRegistrationInput
import com.zitro.games.presentation.common.navigation.input.ticket.model.CPDataTicketApiModel
import com.zitro.games.presentation.common.state.CommonScreenAction
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import com.zitro.games.presentation.common.ui.custom.dialog.ZGPCMessageDialog
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCMessageTypeButton
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCParserButton
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCMessageTypeDialog
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCParserDialog
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.usecase.ZGTDTicketFailureUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketMachineUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegionUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegistrationUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRoomsUseCase
import com.zitro.games.ticket.presentation.registration.dropdown.ZGPCTicketFailureDropDown
import com.zitro.games.ticket.presentation.registration.dropdown.ZGPCTicketMachineDropDown
import com.zitro.games.ticket.presentation.registration.dropdown.ZGPCTicketRegionDropDown
import com.zitro.games.ticket.presentation.registration.dropdown.ZGPCTicketRoomsDropDown
import com.zitro.games.util.common.R
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZGTPTicketRegistrationScreen (
    viewModel: ZGTPTicketRegistrationViewModel,
    registrationInput: CPTicketRegistrationInput,
    navController: NavController
){
    var ticket by remember {
        mutableStateOf(ZGPTRegistrationApiModel())
    }
    val ctx = LocalContext.current
    val scroll = rememberScrollState()
    val roomsApiModel = remember { mutableStateOf(ZGPTRoomsApiModel()) }
    val machineApiModel = remember { mutableStateOf(ZGPTMachineApiModel()) }
    val regionAndFailureApiModel = remember { mutableStateOf(ZGPTRegionWithFailureApiModel()) }
    val regionSelected = remember { mutableStateOf(ZGPTRegionListModel()) }
    val roomSelected = remember { mutableStateOf(ZGPTRoomsListModel()) }
    val machineSelected = remember { mutableStateOf(ZGPTMachineListModel()) }
    val failureSelected = remember { mutableStateOf(ZGPTFailureListModel()) }
    val openDialogMessage = remember { mutableStateOf(false) }
    val openDialogWarning = remember { mutableStateOf(false) }
    val message = remember { mutableStateOf("") }
    val enabled = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.submitAction(ZGTPTicketRegistrationUiAction.Loading(
            registrationInput.dataUser.token ?: ""
        ))
    }

    ZGPCMessageDialog(
        typeDialog =  ZGPCParserDialog.dialog(
            ctx, ZGPCMessageTypeDialog.ZGPC_DIALOG_SUCCESS
        ),
        buttons = ZGPCParserButton.dialogButton(
            ctx, listOf(
                ZGPCMessageTypeButton.ZGPC_BUTTON_ACCEPT,
                ZGPCMessageTypeButton.ZGPC_BUTTON_REASSIGNMENT,
            )
        ),
        message = ctx.getString(
            R.string.zgc_screen_dialog_ticket_registration_message, ticket.registrationId.toString()
        ),
        openDialogCustom = openDialogMessage
    ) {
        when(it){
            ZGPCMessageTypeButton.ZGPC_BUTTON_ACCEPT -> {
                navController.popBackStack()
            }

            ZGPCMessageTypeButton.ZGPC_BUTTON_REASSIGNMENT -> {
                viewModel.submitAction(ZGTPTicketRegistrationUiAction.Technical(
                    registrationInput.dataUser,
                    registrationInput.region,
                    CPDataTicketApiModel(
                        ticketId = ticket.registrationId
                    ),
                ))
            }
        }
    }

    Scaffold(
        topBar = {
            ZGPCTopAppBar(
                "Solicitud de folios"
            )
        }
    ) { contentPadding ->
        ConstraintLayout(
            modifier = Modifier.padding(contentPadding)
        ) {
            val (forms, actions) = createRefs()
            val modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()

            Column(
                modifier = Modifier
                    .constrainAs(forms) {
                        top.linkTo(parent.top)
                        bottom.linkTo(actions.top)
                    }
                    .fillMaxHeight(.9f)
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .verticalScroll(scroll),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ) {

                if (regionAndFailureApiModel.value.listRegions.isNotEmpty()){
                    Text(
                        modifier = Modifier
                            .padding(start = 15.dp),
                        text = ctx.getString(
                            R.string.zgc_screen_ticket_label_status_regions
                        )
                    )

                    val modifier = Modifier.fillMaxWidth().padding(10.dp)

                    ZGPCTicketRegionDropDown(
                        modifier = modifier,
                        data = regionAndFailureApiModel.value.listRegions,
                        selected = regionSelected,
                        enabled = enabled,
                        clear = {
                            roomsApiModel.value = ZGPTRoomsApiModel(listOf())
                            roomSelected.value = ZGPTRoomsListModel()
                        }
                    ){
                        roomsApiModel.value = ZGPTRoomsApiModel(listOf())
                        roomSelected.value = ZGPTRoomsListModel()
                        regionSelected.value.regionId?.let {
                            viewModel.submitAction(ZGTPTicketRegistrationUiAction.LoadingRooms(
                                ZGTDTicketRoomsRequest(
                                    registrationInput.dataUser.token ?: "",
                                    it
                                )
                            ))
                        }
                    }
                }

                if (roomsApiModel.value.listRooms.isNotEmpty()){
                    Text(
                        modifier = Modifier
                            .padding(start = 15.dp),
                        text = ctx.getString(
                            R.string.zgc_screen_ticket_label_status_rooms
                        )
                    )

                    ZGPCTicketRoomsDropDown(
                        modifier = modifier,
                        data = roomsApiModel.value.listRooms,
                        selected = roomSelected,
                        enabled = enabled,
                        clear = {
                            machineApiModel.value = ZGPTMachineApiModel(listOf())
                            machineSelected.value = ZGPTMachineListModel()
                        }
                    ) {
                        machineApiModel.value = ZGPTMachineApiModel(listOf())
                        machineSelected.value = ZGPTMachineListModel()
                        roomSelected.value.officeId?.let {
                            viewModel.submitAction(ZGTPTicketRegistrationUiAction.LoadingMachine(
                                ZGTDTicketMachineRequest(
                                    registrationInput.dataUser.token ?: "",
                                    it
                                )
                            ))
                        }
                    }
                }

                if (machineApiModel.value.listMachine.isNotEmpty()){
                    Text(
                        modifier = Modifier
                            .padding(start = 15.dp),
                        text = ctx.getString(
                            R.string.zgc_screen_ticket_label_status_machine
                        )
                    )

                    ZGPCTicketMachineDropDown(
                        modifier = modifier,
                        data = machineApiModel.value.listMachine,
                        selected = machineSelected,
                        enabled = enabled,
                        clear = {

                        }
                    )
                }

                if (machineSelected.value.machineId == 0
                        && machineApiModel.value.listMachine.isNotEmpty()){
                    machineSelected.value = ZGPTMachineListModel()
                    openDialogWarning.value = true
                    message.value = ctx.getString(
                        R.string.zgc_screen_ticket_label_status_machine_empty
                    )
                }

                ZGPCMessageDialog(
                    typeDialog =  ZGPCParserDialog.dialog(
                        ctx, ZGPCMessageTypeDialog.ZGPC_DIALOG_ERROR
                    ),
                    buttons = ZGPCParserButton.dialogButton(
                        ctx, listOf(
                            ZGPCMessageTypeButton.ZGPC_BUTTON_CONTINUE,
                        )
                    ),
                    message = message.value,
                    openDialogCustom = openDialogWarning,
                    action = {}
                )



                if (regionAndFailureApiModel.value.listFailure.isNotEmpty()){
                    Text(
                        modifier = Modifier
                            .padding(start = 15.dp),
                        text = ctx.getString(
                            R.string.zgc_screen_ticket_label_status_failure
                        )
                    )

                    ZGPCTicketFailureDropDown(
                        modifier = modifier,
                        data = regionAndFailureApiModel.value.listFailure,
                        selected = failureSelected,
                        enabled = enabled,
                        clear = {}
                    ){}
                }

                OutlinedTextField(
                    modifier = modifier,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    label = { Text(text = "Sintoma")},
                    value = ticket.failure,
                    onValueChange = {
                        ticket = ticket.copy(failure = it)
                    }
                )
            }

            ConstraintLayout(
                modifier = Modifier
                    .constrainAs(actions) {
                        top.linkTo(forms.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(.1f)
                    .padding(0.dp),
            ) {
                val (btn1, btn2) = createRefs()

                val request =



                OutlinedButton(
                    modifier = Modifier
                        .constrainAs(btn1) {

                        }
                        .padding(10.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    enabled = roomSelected.value.roomId != null &&
                            registrationInput.dataUser.usuId != null &&
                            machineSelected.value.machineId != null &&
                            failureSelected.value.failureId != null &&
                            registrationInput.dataUser.token != null,
                    onClick = {
                        viewModel.submitAction(ZGTPTicketRegistrationUiAction.Registration(
                            ZGTDTicketRegistrationRequest(
                                roomId = roomSelected.value.roomId ?: 0,
                                usuId = registrationInput.dataUser.usuId ?: 0L,
                                machineId = machineSelected.value.machineId ?: 0,
                                failureId = failureSelected.value.failureId ?: 0,
                                failure = ticket.failure,
                                token = registrationInput.dataUser.token ?: "",
                            )
                        ))
                    }
                ) {
                    Text(text = "Registrar")
                }
            }
        }

        viewModel.uiStateFlow.collectAsState().value.let { state ->
            CommonScreenAction(state = state, onAction = { event, request ->
                when(event){
                    ZGPCMessageTypeButton.ZGPC_BUTTON_RETRY -> {
                        when(request){
                            is ZGTDTicketRegionUseCase.Request ->
                                viewModel.submitAction(ZGTPTicketRegistrationUiAction.LoadingRegion(request.regionRequest))
                            is ZGTDTicketRoomsUseCase.Request ->
                                viewModel.submitAction(ZGTPTicketRegistrationUiAction.LoadingRooms(request.roomRequest))
                            is ZGTDTicketMachineUseCase.Request ->
                                viewModel.submitAction(ZGTPTicketRegistrationUiAction.LoadingMachine(request.machineRequest))
                            is ZGTDTicketFailureUseCase.Request ->
                                viewModel.submitAction(ZGTPTicketRegistrationUiAction.LoadingFailure(request.failureRequest))
                            is ZGTDTicketRegistrationUseCase.Request ->
                                viewModel.submitAction(ZGTPTicketRegistrationUiAction.Registration(request.registrationRequest))
                        }
                    }
                }
            }) {
                when(it){
                    is ZGPTRoomsApiModel -> {
                        if (it.listRooms.isEmpty()){
                            message.value = ctx.getString(R.string.zgc_screen_ticket_label_rooms_empty)
                            openDialogWarning.value = true
                        }
                        roomsApiModel.value = it
                    }
                    is ZGPTRegionWithFailureApiModel -> regionAndFailureApiModel.value = it
                    is ZGPTMachineApiModel -> {
                        if (it.listMachine.isEmpty()){
                            message.value = ctx.getString(R.string.zgc_screen_ticket_label_machine_empty)
                            openDialogWarning.value = true
                        }
                        machineApiModel.value = it
                    }
                    is ZGPTRegistrationApiModel -> {
                        ticket = it
                        enabled.value = false
                        openDialogMessage.value = true
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is ZGTPTicketRegistrationUiSingleEvent.Navigation -> {
                    navController.navigate(it.navRoute)
                    openDialogWarning.value = false
                    openDialogMessage.value = false
                }
            }
        }
    }
}