package com.zitro.games.ticket.presentation.status

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.zitro.games.presentation.common.state.MviViewModel
import com.zitro.games.presentation.common.state.UiSingleEvent
import com.zitro.games.presentation.common.state.UiState
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRoomsUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketStatusUseCase
import com.zitro.games.ticket.presentation.status.converters.ZGTPTicketRoomsConverter
import com.zitro.games.ticket.presentation.status.converters.ZGTPTicketStatusConverter
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
    application: Application
) : MviViewModel<ZGTPTicketApiModel,
        UiState<ZGTPTicketApiModel>,
        ZGTPTicketStatusUiAction, UiSingleEvent>(application) {
    override fun handleAction(action: ZGTPTicketStatusUiAction) {
        when(action) {
            is ZGTPTicketStatusUiAction.Loading -> {
                loadStatus(action.data)
            }

            is ZGTPTicketStatusUiAction.NotLoading -> {

            }

            is ZGTPTicketStatusUiAction.LoadingRooms -> {
                loadRooms(action.data)
            }
        }
    }

    override fun initState(): UiState<ZGTPTicketApiModel> = UiState.Loading

    private fun loadStatus(statusRequest: ZGTDTicketStatusRequest){
        viewModelScope.launch {
            ticketStatusUseCase.execute(ZGTDTicketStatusUseCase.Request(statusRequest)).map {
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
}