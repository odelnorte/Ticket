package com.zitro.games.ticket.presentation.status

import com.zitro.games.presentation.common.state.UiAction
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest

sealed class ZGTPTicketStatusUiAction: UiAction {
    data class Loading(val data: ZGTDTicketStatusRequest) : ZGTPTicketStatusUiAction()
    data class LoadingRooms(val data: ZGTDTicketRoomsRequest) : ZGTPTicketStatusUiAction()
    object NotLoading : ZGTPTicketStatusUiAction()
}