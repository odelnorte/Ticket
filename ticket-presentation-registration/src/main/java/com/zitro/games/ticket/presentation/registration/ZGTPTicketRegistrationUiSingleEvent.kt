package com.zitro.games.ticket.presentation.registration

import com.zitro.games.presentation.common.state.UiSingleEvent

sealed class  ZGTPTicketRegistrationUiSingleEvent: UiSingleEvent {
    data class Navigation(val navRoute: String): ZGTPTicketRegistrationUiSingleEvent()
}