package com.zitro.games.ticket.presentation.adetail

import com.zitro.games.presentation.common.state.UiSingleEvent

sealed class  ZGTPTicketDetailUiSingleEvent: UiSingleEvent {
    data class OpenCamera(val navRoute: String): ZGTPTicketDetailUiSingleEvent()
    data class OpenScanner(val navRoute: String): ZGTPTicketDetailUiSingleEvent()
}