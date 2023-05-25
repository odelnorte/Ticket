package com.zitro.games.ticket.presentation.status

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.zitro.games.presentation.common.state.MviViewModel
import com.zitro.games.presentation.common.state.UiSingleEvent
import com.zitro.games.presentation.common.state.UiState
import com.zitro.games.session.domain.entity.ZGSDUpdateSessionRequest
import com.zitro.games.session.domain.usecase.ZGSDUpdateSessionUseCase
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketUpdateStatusRequest
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegionUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRoomsUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketStatusUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketUpdateStatusUseCase
import com.zitro.games.ticket.presentation.status.converters.ZGTPTicketRegionConverter
import com.zitro.games.ticket.presentation.status.converters.ZGTPTicketRoomsConverter
import com.zitro.games.ticket.presentation.status.converters.ZGTPTicketSessionUpdateStatusConverter
import com.zitro.games.ticket.presentation.status.converters.ZGTPTicketStatusConverter
import com.zitro.games.ticket.presentation.status.converters.ZGTPTicketUpdateStatusConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZGTPTicketStatusViewModel @Inject constructor(
    private val ticketStatusConverter: ZGTPTicketStatusConverter,
    private val ticketStatusUseCase: ZGTDTicketStatusUseCase,
    private val ticketRoomsConverter: ZGTPTicketRoomsConverter,
    private val ticketRoomsUseCase: ZGTDTicketRoomsUseCase,
    private val ticketRegionConverter: ZGTPTicketRegionConverter,
    private val ticketRegionUseCase: ZGTDTicketRegionUseCase,
    private val ticketUpdateStatusConverter: ZGTPTicketUpdateStatusConverter,
    private val ticketUpdateStatusUseCase: ZGTDTicketUpdateStatusUseCase,
    private val sessionUpdateStatusUseCase: ZGSDUpdateSessionUseCase,
    private val sessionUpdateStatusConverter: ZGTPTicketSessionUpdateStatusConverter,
    application: Application
) : MviViewModel<ZGTPTicketApiModel,
        UiState<ZGTPTicketApiModel>,
        ZGTPTicketStatusUiAction, UiSingleEvent>(application) {
    override fun handleAction(action: ZGTPTicketStatusUiAction) {
        when(action) {
            is ZGTPTicketStatusUiAction.Loading -> {
                submitState(UiState.LoadingAction)
                loadStatus(action.data)
            }

            is ZGTPTicketStatusUiAction.NotLoading -> {

            }

            is ZGTPTicketStatusUiAction.LoadingRooms -> {
                submitState(UiState.LoadingAction)
                loadRooms(action.data)
            }

            is ZGTPTicketStatusUiAction.Status -> {
                submitState(UiState.LoadingAction)
                updateStatus(action.data)
            }

            is ZGTPTicketStatusUiAction.LoadingRegions -> {
                submitState(UiState.LoadingAction)
                loadRegions(action.data)
            }
        }
    }

    override fun initState(): UiState<ZGTPTicketApiModel> = UiState.Loading

    private fun loadStatus(statusRequest: ZGTDTicketStatusRequest){
        viewModelScope.launch {
            ticketStatusUseCase.execute(ZGTDTicketStatusUseCase.Request(statusRequest), ).map {
                ticketStatusConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

    private fun loadRooms(statusRequest: ZGTDTicketRoomsRequest){
        viewModelScope.launch {
            ticketRoomsUseCase.execute(ZGTDTicketRoomsUseCase.Request(statusRequest)).map {
                ticketRoomsConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

    private fun loadRegions(statusRequest: ZGTDTicketRegionRequest){
        viewModelScope.launch {
            ticketRegionUseCase.execute(ZGTDTicketRegionUseCase.Request(statusRequest)).map {
                ticketRegionConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

    private fun updateStatus(statusRequest: ZGTDTicketUpdateStatusRequest){
        viewModelScope.launch {
            ticketUpdateStatusUseCase.execute(ZGTDTicketUpdateStatusUseCase.Request(statusRequest)).map {
                ticketUpdateStatusConverter.convert(it)
            }.collect {
                updateSession(statusRequest)
            }
        }
    }

    private fun updateSession(statusRequest: ZGTDTicketUpdateStatusRequest){
        viewModelScope.launch {
            sessionUpdateStatusUseCase.execute(ZGSDUpdateSessionUseCase.Request(
                ZGSDUpdateSessionRequest(
                    usuId = statusRequest.usuId,
                    statusId = statusRequest.statusId,
                    roomId = statusRequest.roomId,
                    regionId = statusRequest.regionId
                )
            )).map {
                sessionUpdateStatusConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }
}