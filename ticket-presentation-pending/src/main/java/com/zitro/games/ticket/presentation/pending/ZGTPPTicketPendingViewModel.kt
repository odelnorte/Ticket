package com.zitro.games.ticket.presentation.pending

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.zitro.games.presentation.common.navigation.NavRoutes
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketDetailInput
import com.zitro.games.presentation.common.navigation.input.ticket.model.CPDataTicketApiModel
import com.zitro.games.presentation.common.state.MviViewModel
import com.zitro.games.presentation.common.state.UiSingleEvent
import com.zitro.games.presentation.common.state.UiState
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.usecase.ZGTDTicketPendingUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRoomsUseCase
import com.zitro.games.ticket.presentation.pending.converters.ZGTPPTicketPendingConverter
import com.zitro.games.ticket.presentation.pending.converters.ZGTPTicketRoomsConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZGTPPTicketPendingViewModel @Inject constructor(
    private val ticketRoomsConverter: ZGTPTicketRoomsConverter,
    private val ticketRoomsUseCase: ZGTDTicketRoomsUseCase,
    private val ticketPendingConverter: ZGTPPTicketPendingConverter,
    private val ticketPendingUseCase: ZGTDTicketPendingUseCase,
    application: Application
) : MviViewModel<ZGTPPTicketPendingApiModel,
        UiState<ZGTPPTicketPendingApiModel>,
        ZGTPTicketPendingUiAction, UiSingleEvent>(application) {
    override fun handleAction(action: ZGTPTicketPendingUiAction) {
        when(action) {
            is ZGTPTicketPendingUiAction.Loading -> {

            }

            is ZGTPTicketPendingUiAction.NotLoading -> {

            }

            is ZGTPTicketPendingUiAction.LoadingTicketPending -> {

            }

            is ZGTPTicketPendingUiAction.LoadingRooms -> {
                loadRooms(action.data)
            }

            is ZGTPTicketPendingUiAction.Detail -> {
                submitSingleEvent(
                    ZGTPTicketPendingUiSingleEvent.Detail(
                        NavRoutes.AtServiceTicketDetail.routeForTicketDetail(
                            CPTicketDetailInput(
                                CPDataTicketApiModel(
                                    action.data.ticket,
                                    action.data.room,
                                    action.data.description,
                                )
                            )
                        )
                    )
                )
            }
        }
    }

    override fun initState(): UiState<ZGTPPTicketPendingApiModel> = UiState.Loading

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