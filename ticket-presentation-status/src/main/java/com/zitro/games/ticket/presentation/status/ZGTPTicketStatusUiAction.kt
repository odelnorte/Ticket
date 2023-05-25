package com.zitro.games.ticket.presentation.status

import com.zitro.games.presentation.common.state.UiAction
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketUpdateStatusRequest

sealed class ZGTPTicketStatusUiAction: UiAction {
    data class Loading(val data: ZGTDTicketStatusRequest) : ZGTPTicketStatusUiAction()
    data class LoadingRooms(val data: ZGTDTicketRoomsRequest) : ZGTPTicketStatusUiAction()
    data class LoadingRegions(val data: ZGTDTicketRegionRequest) : ZGTPTicketStatusUiAction()
    data class Status(val data: ZGTDTicketUpdateStatusRequest) : ZGTPTicketStatusUiAction()
    object NotLoading : ZGTPTicketStatusUiAction()
}