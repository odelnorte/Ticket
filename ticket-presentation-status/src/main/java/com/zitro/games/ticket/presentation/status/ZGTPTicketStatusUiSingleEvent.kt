package com.zitro.games.ticket.presentation.status

import com.zitro.games.presentation.common.state.UiSingleEvent

sealed class  ZGTPTicketStatusUiSingleEvent: UiSingleEvent {
    data class OpenCamera(val navRoute: String): ZGTPTicketStatusUiSingleEvent()
    data class OpenScanner(val navRoute: String): ZGTPTicketStatusUiSingleEvent()
}