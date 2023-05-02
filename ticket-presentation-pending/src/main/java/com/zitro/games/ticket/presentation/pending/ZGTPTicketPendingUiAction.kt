package com.zitro.games.ticket.presentation.pending

import com.zitro.games.presentation.common.state.UiAction
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest

sealed class ZGTPTicketPendingUiAction: UiAction {
    data class Loading(val data: ZGTDTicketStatusRequest) : ZGTPTicketPendingUiAction()
    data class LoadingTicketPending(val data: ZGTDTicketRoomsRequest) : ZGTPTicketPendingUiAction()
    data class LoadingRooms(val data: ZGTDTicketRoomsRequest) : ZGTPTicketPendingUiAction()
    data class Detail(val data: ZGTPPTicketPending) : ZGTPTicketPendingUiAction()
    object NotLoading : ZGTPTicketPendingUiAction()
}