package com.zitro.games.ticket.presentation.technical.detail

import com.zitro.games.presentation.common.state.UiSingleEvent

sealed class  ZGTPTicketTechnicalDetailUiSingleEvent: UiSingleEvent {
    data class OpenCamera(val navRoute: String): ZGTPTicketTechnicalDetailUiSingleEvent()
    data class OpenScanner(val navRoute: String): ZGTPTicketTechnicalDetailUiSingleEvent()
    data class Navigate(val navRoute: String): ZGTPTicketTechnicalDetailUiSingleEvent()
}