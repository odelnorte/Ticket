package com.zitro.games.ticket.presentation.pending

import com.zitro.games.presentation.common.state.UiSingleEvent

sealed class  ZGTPTicketPendingUiSingleEvent: UiSingleEvent {
    data class OpenCamera(val navRoute: String): ZGTPTicketPendingUiSingleEvent()
    data class OpenScanner(val navRoute: String): ZGTPTicketPendingUiSingleEvent()
    data class Detail(val navRoute: String): ZGTPTicketPendingUiSingleEvent()
}