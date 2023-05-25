package com.zitro.games.ticket.presentation.registration

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.zitro.games.presentation.common.navigation.NavRoutes
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalReassignInput
import com.zitro.games.presentation.common.state.MviViewModel
import com.zitro.games.presentation.common.state.UiSingleEvent
import com.zitro.games.presentation.common.state.UiState
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureRequest
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineRequest
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.usecase.ZGTDTicketFailureUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketMachineUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegionUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegionWithFailureUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegistrationUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRoomsUseCase
import com.zitro.games.ticket.presentation.registration.converters.ZGTPTicketFailureConverter
import com.zitro.games.ticket.presentation.registration.converters.ZGTPTicketMachineConverter
import com.zitro.games.ticket.presentation.registration.converters.ZGTPTicketRegionConverter
import com.zitro.games.ticket.presentation.registration.converters.ZGTPTicketRegionWithFailureConverter
import com.zitro.games.ticket.presentation.registration.converters.ZGTPTicketRegistrationConverter
import com.zitro.games.ticket.presentation.registration.converters.ZGTPTicketRoomsConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZGTPTicketRegistrationViewModel @Inject constructor(
    private val ticketRegistrationConverter: ZGTPTicketRegistrationConverter,
    private val ticketRegistrationUseCase: ZGTDTicketRegistrationUseCase,
    private val ticketRoomsConverter: ZGTPTicketRoomsConverter,
    private val ticketRoomsUseCase: ZGTDTicketRoomsUseCase,
    private val ticketMachineConverter: ZGTPTicketMachineConverter,
    private val ticketMachineUseCase: ZGTDTicketMachineUseCase,
    private val ticketRegionUseCase: ZGTDTicketRegionUseCase,
    private val ticketFailureUseCase: ZGTDTicketFailureUseCase,
    private val ticketFailureConverter: ZGTPTicketFailureConverter,
    private val ticketRegionConverter: ZGTPTicketRegionConverter,
    private val ticketRegionWithFailureConverter: ZGTPTicketRegionWithFailureConverter,
    private val ticketRegionWithFailureUseCase: ZGTDTicketRegionWithFailureUseCase,
    application: Application
): MviViewModel<ZGTPTicketRegistrationApiModel,
        UiState<ZGTPTicketRegistrationApiModel>,
        ZGTPTicketRegistrationUiAction, UiSingleEvent>(application) {

    override fun handleAction(action: ZGTPTicketRegistrationUiAction) {
        when(action) {
            is ZGTPTicketRegistrationUiAction.Loading -> {
                submitState(UiState.LoadingAction)
                loadRegionAndFailure(action.token)
            }

            is ZGTPTicketRegistrationUiAction.LoadingRooms -> {
                submitState(UiState.LoadingAction)
                loadRooms(action.data)
            }

            is ZGTPTicketRegistrationUiAction.LoadingMachine -> {
                submitState(UiState.LoadingAction)
                loadMachine(action.data)
            }

            is ZGTPTicketRegistrationUiAction.LoadingRegion -> {
                submitState(UiState.LoadingAction)
                loadRegion(action.data)
            }

            is ZGTPTicketRegistrationUiAction.LoadingFailure -> {
                submitState(UiState.LoadingAction)
                loadFailure(action.data)
            }

            is ZGTPTicketRegistrationUiAction.Registration -> {
                submitState(UiState.LoadingAction)
                registration(action.data)
            }

            is ZGTPTicketRegistrationUiAction.NotLoading -> {

            }

            is ZGTPTicketRegistrationUiAction.Technical -> {
                submitSingleEvent(
                    ZGTPTicketRegistrationUiSingleEvent.Navigation(
                        NavRoutes.AtServiceTicketReassignTechnical.routeForTicketTechnical(
                            CPTicketTechnicalReassignInput(
                                action.data,
                                action.region,
                                action.ticket
                            )
                        )
                    )
                )

            }
        }
    }

    private fun loadRooms(roomRequest: ZGTDTicketRoomsRequest) {
        viewModelScope.launch {
            ticketRoomsUseCase.execute(ZGTDTicketRoomsUseCase.Request(roomRequest)).map {
                ticketRoomsConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

    private fun loadMachine(machineRequest: ZGTDTicketMachineRequest) {
        viewModelScope.launch {
            ticketMachineUseCase.execute(ZGTDTicketMachineUseCase.Request(machineRequest)).map {
                ticketMachineConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

    private fun loadRegion(regionRequest: ZGTDTicketRegionRequest) {
        viewModelScope.launch {
            ticketRegionUseCase.execute(ZGTDTicketRegionUseCase.Request(regionRequest)).map {
                ticketRegionConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

    private fun loadFailure(failureRequest: ZGTDTicketFailureRequest) {
        viewModelScope.launch {
            ticketFailureUseCase.execute(ZGTDTicketFailureUseCase.Request(failureRequest)).map {
                ticketFailureConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

    private fun loadRegionAndFailure(token: String) {
        viewModelScope.launch {
            ticketRegionWithFailureUseCase.execute(ZGTDTicketRegionWithFailureUseCase.Request(
                ZGTDTicketRegionRequest(token),
                ZGTDTicketFailureRequest(token)
            )).map {
                ticketRegionWithFailureConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

    private fun registration(registration: ZGTDTicketRegistrationRequest) {
        viewModelScope.launch {
            ticketRegistrationUseCase.execute(ZGTDTicketRegistrationUseCase.Request(
                registration
            )).map {
                ticketRegistrationConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

    override fun initState(): UiState<ZGTPTicketRegistrationApiModel> = UiState.NotLoading
}