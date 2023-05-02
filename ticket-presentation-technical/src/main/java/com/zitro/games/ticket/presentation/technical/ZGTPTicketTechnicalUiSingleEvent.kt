package com.zitro.games.ticket.presentation.technical

import com.zitro.games.presentation.common.state.UiSingleEvent

sealed class  ZGTPTicketTechnicalUiSingleEvent: UiSingleEvent {
    data class OpenCamera(val navRoute: String): ZGTPTicketTechnicalUiSingleEvent()
    data class OpenScanner(val navRoute: String): ZGTPTicketTechnicalUiSingleEvent()
    data class Navigate(val navRoute: String): ZGTPTicketTechnicalUiSingleEvent()
}